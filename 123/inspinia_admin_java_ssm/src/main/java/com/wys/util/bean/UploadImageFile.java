package com.wys.util.bean;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by wangyushuai@fang.com on 2018/3/15.
 */
public class UploadImageFile {
    MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
