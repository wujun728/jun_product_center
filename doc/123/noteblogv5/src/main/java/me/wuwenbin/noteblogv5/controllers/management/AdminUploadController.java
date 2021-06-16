package me.wuwenbin.noteblogv5.controllers.management;

import cn.hutool.core.img.Img;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import me.wuwenbin.noteblogv5.constant.NBV5;
import me.wuwenbin.noteblogv5.enums.ObjectKeyEnum;
import me.wuwenbin.noteblogv5.constant.uploader.LayUploader;
import me.wuwenbin.noteblogv5.controllers.BaseController;
import me.wuwenbin.noteblogv5.mapper.UploadMapper;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import me.wuwenbin.noteblogv5.model.entity.Upload;
import me.wuwenbin.noteblogv5.model.entity.User;
import me.wuwenbin.noteblogv5.service.UserService;
import me.wuwenbin.noteblogv5.service.ParamService;
import me.wuwenbin.noteblogv5.utils.NbUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.File;
import java.util.Objects;

/**
 * created by Wuwenbin on 2018/8/3 at 22:06
 *
 * @author wuwenbin
 */
@Slf4j
@RestController
@RequestMapping("/management")
public class AdminUploadController extends BaseController {

    private final HttpServletRequest request;
    private final UserService userService;
    private final ParamService paramService;

    private final String EDITOR_MD_FILE_NAME = "editormd-image-file";
    private final String LAY_UPLOADER_FILE_NAME = "file";

    public AdminUploadController(HttpServletRequest request, UserService userService, ParamService paramService) {
        this.request = request;
        this.userService = userService;
        this.paramService = paramService;
    }


    @PostMapping("/upload")
    public Object upload(@RequestParam(value = LAY_UPLOADER_FILE_NAME, required = false) MultipartFile file,
                         @RequestParam("reqType") String reqType,
                         @RequestParam("objectKeyCode") int objectKeyCode,
                         String generateNextArticleId) {
        String base64 = request.getParameter("base64");
        String graffiti = "1";
        if (StrUtil.isNotEmpty(base64) && graffiti.equals(base64)) {
            String base64Str = request.getParameter("img_base64_data");
            file = NbUtils.base64ToMultipartFile(base64Str);
        }
        if (objectKeyCode >= 9 && objectKeyCode <= 13) {
            ObjectKeyEnum objectKeyEnum = ObjectKeyEnum.getObjectKeyEnumByCode(objectKeyCode);
            NbUtils.deleteTemp(objectKeyEnum);
        }
        User su = getSessionUser(request);
        Object obj = Objects.requireNonNull(NbUtils.getUploadServiceByConfig())
                .doUpload(
                        su.getId(), file, reqType,
                        MapUtil.of(new Object[][]{{"objectKey", objectKeyCode}, {"objectKeyId", generateNextArticleId}}));
        if (objectKeyCode == 9) {
            if (obj instanceof LayUploader) {
                LayUploader layUploader = (LayUploader) obj;
                Object newAvatar = layUploader.getData().get("src");
                scaleAvatar(newAvatar.toString());
                boolean r = userService.update(Wrappers.<User>update().set("avatar", newAvatar).eq("id", su.getId()));
                if (r) {
                    paramService.update(NBV5.INFO_LABEL_LOGO, newAvatar.toString());
                }
            } else {
                return ResultBeanObj.error("上传对象获取失败，请稍后再试！");
            }
        }
        return obj;
    }


    @PostMapping("/upload/editorMD")
    public Object uploadEditorMd(@RequestParam(value = EDITOR_MD_FILE_NAME) MultipartFile file,
                                 @RequestParam("objectKeyCode") int objectKeyCode, String generateNextArticleId) {
        User su = getSessionUser(request);
        return Objects.requireNonNull(NbUtils.getUploadServiceByConfig())
                .doUpload(
                        su.getId(), file, "md",
                        MapUtil.of(new Object[][]{{"objectKey", objectKeyCode}, {"objectKeyId", generateNextArticleId}}));
    }

    private void scaleAvatar(String imgSrc) {
        UploadMapper uploadMapper = NbUtils.getBean(UploadMapper.class);
        Upload upload = uploadMapper.selectOne(Wrappers.<Upload>query().eq("virtual_path", imgSrc));
        String trueDiskPath = upload.getDiskPath();
        File f = new File(trueDiskPath);
        Img img = Img.from(f);
        int width = img.scale(1).getImg().getWidth(null);
        int height = img.scale(1).getImg().getHeight(null);
        int num = Math.min(width, height);
        ImgUtil.cut(f, f, new Rectangle(0, 0, num, num));
    }
}
