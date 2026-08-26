package com.ruoyi.file.storage.enums;


public enum StorageTypeEnum {
    LOCAL("local", "本地存储"),
    ALIYUN_OSS("ali", "阿里云OSS对象存储"),
    FAST_DFS("dfs", "fastDFS集群存储"),
    MINIO("minio", "minio存储"),
    QINIUYUN_KODO("qny", "七牛云KODO对象存储"),
    TENCENT_COS("tencent", "腾讯云COS对象存储");
    private final String code;
    private final String name;

    StorageTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static StorageTypeEnum getByCode(String code) {
        for (StorageTypeEnum storageTypeEnum : StorageTypeEnum.values()) {
            if (storageTypeEnum.getCode().equals(code)) {
                return storageTypeEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }


    public String getName() {
        return name;
    }

}
