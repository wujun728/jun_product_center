package cc.mrbird.web.controller.security;

import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.system.domain.MyUser;
import cc.mrbird.system.service.UserService;
import cc.mrbird.web.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MobileController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping("/mobile/bind")
    public ResponseBo mobileBind(String mobile) {
        try {
            MyUser user = userService.findByNameOrMobile(mobile);
            if (user != null)
                return ResponseBo.error("该手机号已绑定别的账号，请勿重复绑定！");
            MyUser currentUser = super.getCurrentUser();
            this.userService.mobileBind(currentUser.getUsername(), mobile);
            return ResponseBo.ok();
        } catch (Exception e) {
            log.error("绑定失败", e);
            return ResponseBo.error("绑定失败，请联系网站管理员！");
        }
    }

    @GetMapping("/mobile/unbind")
    public ResponseBo mobileUnbind(String mobile) {
        try {
            MyUser currentUser = super.getCurrentUser();
            this.userService.mobileUnbind(currentUser.getUsername(), mobile);
            return ResponseBo.ok();
        } catch (Exception e) {
            log.error("解绑失败", e);
            return ResponseBo.error("解绑失败，请联系网站管理员！");
        }
    }
}
