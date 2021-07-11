package me.wuwenbin.noteblogv5.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import me.wuwenbin.noteblogv5.constant.NBV5;
import me.wuwenbin.noteblogv5.enums.ObjectKeyEnum;
import me.wuwenbin.noteblogv5.enums.RoleEnum;
import me.wuwenbin.noteblogv5.constant.UploadConstant;
import me.wuwenbin.noteblogv5.exception.AppRunningException;
import me.wuwenbin.noteblogv5.initialization.InitListener;
import me.wuwenbin.noteblogv5.model.bo.Base64MultipartFile;
import me.wuwenbin.noteblogv5.model.bo.IpInfo;
import me.wuwenbin.noteblogv5.model.entity.Article;
import me.wuwenbin.noteblogv5.model.entity.Param;
import me.wuwenbin.noteblogv5.model.entity.User;
import me.wuwenbin.noteblogv5.service.UserService;
import me.wuwenbin.noteblogv5.service.ArticleService;
import me.wuwenbin.noteblogv5.service.ParamService;
import me.wuwenbin.noteblogv5.service.UploadService;
import org.springframework.beans.BeansException;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;
import static me.wuwenbin.noteblogv5.enums.ObjectKeyEnum.*;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * created by Wuwenbin on 2018/7/14 at 10:33
 *
 * @author wuwenbin
 */
@Component
@Slf4j
public class NbUtils implements ApplicationContextAware, ServletContextListener {

    private static final String UNKNOWN = "unknown";
    private static ApplicationContext applicationContext = null;
    private static ServletContext servletContext = null;

    /**
     * 获取实际ip地址
     *
     * @param request
     * @return
     */
    public static String getRemoteAddress(HttpServletRequest request) {
        String remoteAddress;
        try {
            remoteAddress = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(remoteAddress) || UNKNOWN.equalsIgnoreCase(remoteAddress)) {
                remoteAddress = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(remoteAddress) || remoteAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(remoteAddress)) {
                remoteAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(remoteAddress) || UNKNOWN.equalsIgnoreCase(remoteAddress)) {
                remoteAddress = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(remoteAddress) || UNKNOWN.equalsIgnoreCase(remoteAddress)) {
                remoteAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(remoteAddress) || UNKNOWN.equalsIgnoreCase(remoteAddress)) {
                remoteAddress = request.getRemoteAddr();
            }
        } catch (Exception var3) {
            remoteAddress = request.getRemoteAddr();
        }
        return remoteAddress;
    }


    /**
     * 获取指定HTML标签的指定属性的值
     *
     * @param source  要匹配的源文本
     * @param element 标签名称
     * @param attr    标签的属性名称
     * @return 属性值列表
     */
    public static List<String> getTagAttrValue(String source, String element, String attr) {
        List<String> result = new ArrayList<>();
        String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?(\\s.*?)?>";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result.add(r);
        }
        return result;
    }

    /**
     * @param source  要匹配的源文本
     * @param element 标签名称
     * @return 内容集合
     */
    public static List<String> getTagContent(String source, String element) {
        List<String> result = new ArrayList<>();
        String reg = "<" + element + ">" + "(.+?)</" + element + ">";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result.add(r);
        }
        return result;
    }

    /**
     * 替换指定标签内容包括标签
     *
     * @param source
     * @param element
     * @param replacement
     * @return
     */
    public static String replaceTagContent(String source, String element, String replacement) {
        String reg = "<" + element + ">" + "(.+?)</" + element + ">";
        Matcher m = Pattern.compile(reg).matcher(source);
        return m.replaceAll(replacement);
    }

    /**
     * 获取当前的request对象
     *
     * @return
     */
    public static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Objects.requireNonNull(attributes).getRequest();
    }


    /**
     * 获取配置文件的属性值
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getEnvPropertyByKey(String key, Class<T> clazz) {
        return getBean(Environment.class).getProperty(key, clazz);
    }

    /**
     * 获取配置文件的属性，有默认值
     *
     * @param key
     * @param clazz
     * @param defaultValue
     * @param <T>
     * @return
     */
    public static <T> T getEnvPropertyByKey(String key, Class<T> clazz, T defaultValue) {
        T t = getEnvPropertyByKey(key, clazz);
        return t == null ? defaultValue : t;
    }

    /**
     * 获取工程的发布路径根目录
     * 即classes的绝对路径
     * file:/E:/idea_workplace/target/classes/
     *
     * @return
     */
    public static String getClassesPath() {
        return Objects.requireNonNull(NbUtils.class.getClassLoader().getResource("")).getPath();
    }

    /**
     * 获取改文件在工程中所在的完整绝对路径
     *
     * @param filePath 相对classes的路径
     * @return
     */
    public static String getFilePathInClassesPath(String filePath) {
        return getClassesPath() + filePath;
    }

    /**
     * 判断是否为ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return StrUtil.isNotBlank(request.getHeader("x-requested-with")) && "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
    }

    /**
     * 是否为 json 请求
     *
     * @param request
     * @return
     */
    public static boolean isJson(HttpServletRequest request) {
        String headerAccept = request.getHeader("Accept");
        return !isEmpty(headerAccept) && headerAccept.contains("application/json");
    }

    /**
     * 是否为get请求
     *
     * @param request
     * @return
     */
    public static boolean isGetRequest(HttpServletRequest request) {
        String method = request.getMethod();
        return "GET".equalsIgnoreCase(method);
    }

    /**
     * 是否为 router 请求
     *
     * @param request
     * @return
     */
    public static boolean isRouterRequest(HttpServletRequest request) {
        String headerAccept = request.getHeader("Accept");
        return !isEmpty(headerAccept) && headerAccept.contains("text/html") && !isJson(request) && isAjaxRequest(request) && isGetRequest(request);
    }

    /**
     * 获取Bean
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

    /**
     * 获取bean
     *
     * @param beanName
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        return applicationContext.getBean(beanName, clazz);
    }


    /**
     * 获取ip地理信息位置
     *
     * @param ip
     * @return
     */
    public static IpInfo getIpInfo(String ip) {
        String url = "http://whois.pconline.com.cn/ip.jsp?ip={}";
        url = StrUtil.format(url, ip);
        String resp = HttpUtil.get(url);
        log.info("获取 ip详细地址，参数：{}", ip);
        try {
            JSON json = JSONUtil.parse(resp);
            String result = json.toString();
            String[] res = result.split("\\s+");
            return res.length > 1 ?
                    IpInfo.builder().address(StringUtils.isEmpty(res[0]) ? "未知" : res[0]).line(res[1]).build() :
                    IpInfo.builder().address(res[0]).build();
        } catch (cn.hutool.json.JSONException je) {
            String[] res = resp.split(" ");
            return res.length > 1 ?
                    IpInfo.builder().address(StringUtils.isEmpty(res[0]) ? "未知" : res[0]).line(res[1]).build() :
                    IpInfo.builder().address(res[0]).build();
        } catch (Exception e) {
            log.error("获取ip地理位置信息失败", e);
            return IpInfo.builder().address("未知位置").build();
        }
    }

    /**
     * 根据设定的上传方式（本地服务器上传还是七牛云上传）来匹配相应的service实例
     *
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> UploadService<T> getUploadServiceByConfig() {
        final String name = NBV5.UPLOAD_TYPE;
        final String local = "local", qiniu = "qiniu";
        String config = applicationContext.getBean(ParamService.class).getOne(Wrappers.<Param>query().eq("name", name)).getValue();
        if (config != null) {
            UploadConstant.Method method = UploadConstant.Method.getMethodByName(config);
            if (local.equalsIgnoreCase(method.name())) {
                return applicationContext.getBean("localUpload", UploadService.class);
            } else if (qiniu.equalsIgnoreCase(method.name())) {
                return applicationContext.getBean("qiniuUpload", UploadService.class);
            } else {
                throw new AppRunningException("未找到相应的上传类型的Service实例！");
            }
        }
        return null;
    }

    /**
     * base64转multipart file
     *
     * @param base64
     * @return
     */
    public static MultipartFile base64ToMultipartFile(String base64) {
        try {
            String[] baseStrs = base64.split(",");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b;
            b = decoder.decodeBuffer(baseStrs[1]);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new Base64MultipartFile(b, baseStrs[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 判断全角的字符串，包括全角汉字以及全角标点
     *
     * @param charSequence
     * @return
     */
    public static int fullAngelWords(CharSequence charSequence) {
        if (charSequence == null) {
            return 0;
        }
        char[] t1 = charSequence.toString().toCharArray();
        int count = 0;
        for (char aT1 : t1) {
            if (Character.toString(aT1).matches("[^\\x00-\\xff]")) {
                System.out.println(aT1);
                count++;
            }
        }
        return count;
    }


    /**
     * @param value 待处理内容
     * @return
     * @Description 过滤XSS脚本内容
     */
    public static String stripXss(String value) {
        String rlt = null;

        if (null != value) {
            rlt = value.replaceAll("", "");
            Pattern scriptPattern = compile("<script>(.*?)</script>", CASE_INSENSITIVE);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            scriptPattern = compile("</script>", CASE_INSENSITIVE);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            scriptPattern = compile("<script(.*?)>", CASE_INSENSITIVE
                    | MULTILINE | DOTALL);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            scriptPattern = compile("eval\\((.*?)\\)", CASE_INSENSITIVE
                    | MULTILINE | DOTALL);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            scriptPattern = compile("expression\\((.*?)\\)", CASE_INSENSITIVE
                    | MULTILINE | DOTALL);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            scriptPattern = compile("javascript:", CASE_INSENSITIVE);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            scriptPattern = compile("vbscript:", CASE_INSENSITIVE);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            scriptPattern = compile("onload(.*?)=", CASE_INSENSITIVE
                    | MULTILINE | DOTALL);
            rlt = scriptPattern.matcher(rlt).replaceAll("");
        }

        return rlt;
    }

    /**
     * @param value 待处理内容
     * @return
     * @Description 过滤SQL注入内容
     */
    public static String stripSqlInjection(String value) {
        return (null == value) ? null : value.replaceAll("('.+--)|(--)|(%7C)", "");
    }

    /**
     * @param value 待处理内容
     * @return
     * @Description 过滤SQL/XSS注入内容
     */
    public static String stripSqlXss(String value) {
        return stripXss(stripSqlInjection(value));
    }


    public static Map<String, Object> toLowerKeyMap(Map<String, Object> originMap) {
        if (CollectionUtils.isEmpty(originMap)) {
            return null;
        }
        Map<String, Object> newMap = new TreeMap<>();
        originMap.forEach((k, v) -> newMap.put(k.toLowerCase(), v));
        return newMap;
    }

    public static List<Map<String, Object>> toLowerKeyListMap(List<Map<String, Object>> originListMap) {
        if (CollectionUtils.isEmpty(originListMap)) {
            return null;
        }
        List<Map<String, Object>> newListMap = new LinkedList<>();
        originListMap.forEach(m -> newListMap.add(toLowerKeyMap(m)));
        return newListMap;
    }

    /**
     * 执行jar所在的目录路径，可能存在null问题，修改为 usr.dir
     *
     * @return
     */
    public static String rootPath() {
//        ApplicationHome applicationHome = new ApplicationHome(InitListener.class);
//        File jar = applicationHome.getSource();
//        return jar.getParentFile().toString();
        return System.getProperty("user.dir");
    }
    /**
     * 安装文件是否已存在
     *
     * @return
     */
    public static boolean installedFileIsExist() {
        String filePath = rootPath().concat("/nbv5.installed");
        return new File(filePath).exists();
    }

    /**
     * 检测是否已经安装
     *
     * @return
     */
    public static boolean noteBlogIsInstalled() {
        boolean appInstalled = NbUtils.installedFileIsExist();
        Param initStatusParam = NbUtils.getBean(ParamService.class).getInitStatus();
        return appInstalled && initStatusParam != null && "1".equals(initStatusParam.getValue());
    }

    /**
     * 删除未保存的文章遗留的文件和图片
     */
    public static void deleteUploadTempWhenArticleNotSave() {
        UserService userService = NbUtils.getBean(UserService.class);
        List<User> users = userService.list(Wrappers.<User>query().eq("role", RoleEnum.ADMIN.getValue()));
        List<Long> userIds = new ArrayList<>(users.size());
        for (User user : users) {
            userIds.add(user.getId());
        }
        NamedParameterJdbcTemplate jdbcTemplate = NbUtils.getBean(NamedParameterJdbcTemplate.class);
        String sql = "select disk_path from nb_upload " +
                "WHERE object_key_id IS NOT NULL " +
                "AND object_key_id NOT IN(SELECT id FROM nb_article) " +
                "AND user_id IN (:userIds) ";
        Map<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("userIds", userIds);
        List<String> paths = jdbcTemplate.queryForList(sql, paramMap, String.class);
        for (String path : paths) {
            File file = new File(path);
            //noinspection ResultOfMethodCallIgnored
            file.delete();
        }
        String sql1 = "DELETE FROM nb_upload " +
                "WHERE object_key_id IS NOT NULL " +
                "AND object_key_id NOT IN(SELECT id FROM nb_article) " +
                "AND user_id IN (:userIds) ";
        jdbcTemplate.update(sql1, paramMap);

    }


    /**
     * 文章编辑时，图片可能会修改或者删除
     * 这些图片也要定时删除
     *
     * @param articleId
     */
    public static void deleteUploadTempOnArticleUpdate(String articleId) {
        ArticleService articleService = NbUtils.getBean(ArticleService.class);
        UserService userService = NbUtils.getBean(UserService.class);
        Article article = articleService.getById(articleId);
        articleService.handleShowArticle(article, userService.getById(1));
        JdbcTemplate jdbcTemplate = NbUtils.getBean(JdbcTemplate.class);
        //文章内上传
        String sql = "select disk_path from nb_upload where locate(virtual_path,?)=0 AND object_key =? AND object_key_id = ?";
        List<String> keys = Arrays.asList(
                MANAGEMENT_ARTICLE_ADD_EDITORMD.name().toLowerCase(),
                MANAGEMENT_ARTICLE_ADD_NKINDEDITOR.name().toLowerCase(),
                MANAGEMENT_ARTICLE_EDIT_EDITORMD.name().toLowerCase(),
                MANAGEMENT_ARTICLE_EDIT_NKINDEDITOR.name().toLowerCase());
        for (String key : keys) {
            List<String> paths = jdbcTemplate.queryForList(sql, String.class, article.getContent(), key, article.getId());
            for (String path : paths) {
                File file = new File(path);
                //noinspection ResultOfMethodCallIgnored
                file.delete();
            }
            String sql1 = "delete from nb_upload where locate(virtual_path,?)=0 AND object_key =? AND object_key_id = ?";
            jdbcTemplate.update(sql1, article.getContent(), key, article.getId());
        }

        //文章封面
        String sql3 = "select disk_path from nb_upload where virtual_path!=? AND object_key =? AND object_key_id = ?";
        List<String> keys2 = Arrays.asList(
                MANAGEMENT_ARTICLE_ADD_COVER.name().toLowerCase(),
                MANAGEMENT_ARTICLE_EDIT_COVER.name().toLowerCase());
        for (String key : keys2) {
            List<String> paths2 = jdbcTemplate.queryForList(sql3, String.class, article.getCover(), key, article.getId());
            for (String path : paths2) {
                File file = new File(path);
                //noinspection ResultOfMethodCallIgnored
                file.delete();
            }
            String sql4 = "delete from nb_upload where virtual_path!=? AND object_key =? AND object_key_id = ?";
            jdbcTemplate.update(sql4, article.getCover(), key, article.getId());
        }
    }

    /**
     * 文章删除时，相关图片也删除
     *
     * @param articleId
     */
    public static void deleteUploadTempOnArticleDelete(String articleId) {
        JdbcTemplate jdbcTemplate = NbUtils.getBean(JdbcTemplate.class);
        String sql = "select disk_path from nb_upload where object_key_id = ?";
        List<String> paths = jdbcTemplate.queryForList(sql, String.class, articleId);
        for (String path : paths) {
            File file = new File(path);
            //noinspection ResultOfMethodCallIgnored
            file.delete();
        }
        String sql1 = "delete from nb_upload where object_key_id = ?";
        jdbcTemplate.update(sql1, articleId);
    }

    public static void deleteTemp(ObjectKeyEnum objectKeyEnum) {
        JdbcTemplate jdbcTemplate = NbUtils.getBean(JdbcTemplate.class);
        String sql = "select disk_path from nb_upload where object_key = ?";
        List<String> paths = jdbcTemplate.queryForList(sql, String.class, objectKeyEnum.name().toLowerCase());
        for (String path : paths) {
            File file = new File(path);
            //noinspection ResultOfMethodCallIgnored
            file.delete();
        }
        String sql1 = "delete from nb_upload where object_key = ?";
        jdbcTemplate.update(sql1, objectKeyEnum.name().toLowerCase());
    }


    public static ApplicationContext getApplicationContext() {
        return NbUtils.applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        NbUtils.applicationContext = applicationContext;
    }

    public static ServletContext getServletContext() {
        return NbUtils.servletContext;
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        NbUtils.servletContext = sce.getServletContext();
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        NbUtils.servletContext = null;
    }
}
