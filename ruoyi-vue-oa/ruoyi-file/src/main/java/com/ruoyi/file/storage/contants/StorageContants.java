package com.ruoyi.file.storage.contants;

import java.util.Arrays;
import java.util.List;

/**
 * 常量
 * @Author wocurr.com
 */
public class StorageContants {

    /**
     * 上传锁key
     */
    public static final String UPLOAD_LOCK_KEY = "uploader:lock:";

    /**
     * 分片上传key
     */
    public static final String UPLOAD_CHUNKNUM_LOCK_KEY = "uploader:chunk:";

    /**
     * 分片上传分片结果key
     */
    public static final String UPLOAD_PARTRESULT_LOCK_KEY = "uploader:partresult:";

    public static final String UPLOAD_CHUNKSIZE_LOCK_KEY = "uploader:size:lock:";
    public static final String UPLOAD_CHUNKPATH_LOCK_KEY = "uploader:path:lock:";

    public static final List<String> DOCS = Arrays.asList("doc", "docx", "dot", "dotx");

    public static final String PDF = "pdf";

}
