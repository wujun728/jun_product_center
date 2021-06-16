package me.wuwenbin.noteblogv5.constant;

import me.wuwenbin.noteblogv5.exception.AppRunningException;

/**
 * 上传相关的常量
 * created by Wuwenbin on 2018/7/16 at 15:26
 *
 * @author wuwenbin
 */
public interface UploadConstant {

    /**
     * 上传方式类型
     * 本地上传和七牛云服务器上传
     */
    enum Method {
        /**
         * 本地上传
         */
        LOCAL,

        /**
         * 七牛云上传
         */
        QINIU;

        /**
         * 根据name查找Method
         *
         * @param name
         * @return
         */
        public static Method getMethodByName(String name) {
            if (name.equalsIgnoreCase(LOCAL.name())) {
                return LOCAL;
            } else if (name.equalsIgnoreCase(QINIU.name())) {
                return QINIU;
            } else {
                throw new AppRunningException("没有找到匹配的上传接口方法类型！");
            }
        }
    }

    /**
     * 上传文件的类型
     */
    interface FileType {
        /**
         * 图片
         */
        String IMAGE = "/img";

        /**
         * 非图片文件
         */
        String FILE = "/file";

        /**
         * 访问文件的虚拟路径前缀
         */
        String VISIT_PATH = "/upfiles";
    }
}
