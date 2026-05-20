package com.ruoyi.file.storage.upload;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.sign.Md5Utils;
import com.ruoyi.file.storage.contants.StorageContants;
import com.ruoyi.file.storage.upload.domain.UploadFile;
import com.ruoyi.file.storage.upload.domain.UploadFileResult;
import com.ruoyi.file.storage.upload.param.MultipartFileParam;
import com.ruoyi.tools.lock.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 文件上传父类
 */
@Slf4j
@Component
public abstract class Uploader {
    @Autowired
    private RedisLock redisLock;
    @Autowired
    private RedisCache redisCache;

    /**
     * 普通上传
     *
     * @param httpServletRequest http的request请求
     * @return 文件列表
     */
    public List<UploadFileResult> upload(HttpServletRequest httpServletRequest) {
        UploadFile uploadFile = new UploadFile();
        uploadFile.setChunkNumber(1);
        uploadFile.setChunkSize(0);
        uploadFile.setTotalChunks(1);
        uploadFile.setIdentifier(UUID.randomUUID().toString());
        return upload(httpServletRequest, uploadFile);
    }

    /**
     * 分片上传
     *
     * @param httpServletRequest http的request请求
     * @param uploadFile         分片上传参数
     * @return 文件列表
     */
    public List<UploadFileResult> upload(HttpServletRequest httpServletRequest, UploadFile uploadFile) {
        List<UploadFileResult> uploadFileResultList = new ArrayList<>();
        StandardMultipartHttpServletRequest request = (StandardMultipartHttpServletRequest) httpServletRequest;
        if (request.getContentType() == null || !request.getContentType().startsWith("multipart/")) {
            throw new BaseException("未包含文件上传域");
        }
        try {
            Iterator<String> iter = request.getFileNames();
            while (iter.hasNext()) {
                List<MultipartFile> multipartFileList = request.getFiles(iter.next());
                for (MultipartFile multipartFile : multipartFileList) {
                    MultipartFileParam multipartFileParam = new MultipartFileParam(multipartFile);
                    UploadFileResult uploadFileResult = doUploadFlow(multipartFileParam, uploadFile);
                    uploadFileResultList.add(uploadFileResult);
                }
                iter.remove();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("上传失败");
        }
        return uploadFileResultList;
    }

    /**
     * 上传文件流
     *
     * @param multipartFileParam
     * @param uploadFile
     * @return
     */
    protected UploadFileResult doUploadFlow(MultipartFileParam multipartFileParam, UploadFile uploadFile) {
        UploadFileResult uploadFileResult;
        try {
            rectifier(multipartFileParam, uploadFile);
            uploadFileResult = organizationalResults(multipartFileParam, uploadFile);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("上传失败");
        }
        return uploadFileResult;
    }

    /**
     * 取消上传
     *
     * @param uploadFile 分片上传参数
     */
    public abstract void cancelUpload(UploadFile uploadFile);

    protected abstract void doUploadFileChunk(MultipartFileParam multipartFileParam, UploadFile uploadFile) throws IOException;

    protected abstract UploadFileResult organizationalResults(MultipartFileParam multipartFileParam, UploadFile uploadFile);

    /**
     * 整流
     *
     * @param multipartFileParam
     * @param uploadFile
     */
    private void rectifier(MultipartFileParam multipartFileParam, UploadFile uploadFile) {
        // 空文件的identifier相同，所以要加上文件名的md5
        String userId = SecurityUtils.getUserId();
        String fileHash = Md5Utils.hash(uploadFile.getFilename() + uploadFile.getIdentifier());
        String key = StorageContants.UPLOAD_LOCK_KEY + userId + Constants.COLON + fileHash;
        redisLock.lock(key);
        String currentChunkKey = StorageContants.UPLOAD_CHUNKNUM_LOCK_KEY + userId + Constants.COLON + fileHash;
        try {
            if (!redisCache.hasKey(currentChunkKey)) {
                redisCache.setCacheObject(currentChunkKey, 1, 1000 * 60 * 60L);
            }
            int currentUploadChunkNumber = redisCache.getCacheObject(currentChunkKey);
            if (uploadFile.getChunkNumber() != currentUploadChunkNumber) {
                redisLock.unlock(key);
                Thread.sleep(100);
                while (redisLock.tryLock(key, 30L, 30L, TimeUnit.SECONDS)) {
                    currentUploadChunkNumber = redisCache.getCacheObject(currentChunkKey);
                    if (uploadFile.getChunkNumber() <= currentUploadChunkNumber) {
                        break;
                    }
                    if (Math.abs(currentUploadChunkNumber - uploadFile.getChunkNumber()) > 2) {
                        log.error("传入的切片数据异常，当前应上传切片为第{}块，传入的为第{}块。", currentUploadChunkNumber, uploadFile.getChunkNumber());
                        throw new BaseException("切片数据异常");
                    }
                    redisLock.unlock(key);
                }
            }
            log.info("文件名{}，正在上传第{}块, 共{}块", multipartFileParam.getMultipartFile().getOriginalFilename(), uploadFile.getChunkNumber(), uploadFile.getTotalChunks());
            if (uploadFile.getChunkNumber() == currentUploadChunkNumber) {
                doUploadFileChunk(multipartFileParam, uploadFile);
                log.info("文件名{}，第{}块上传成功", multipartFileParam.getMultipartFile().getOriginalFilename(), uploadFile.getChunkNumber());
                redisCache.getIncr(currentChunkKey);
            }
        } catch (Exception e) {
            log.error("第{}块上传失败", uploadFile.getChunkNumber());
            redisCache.setCacheObject(currentChunkKey, uploadFile.getChunkNumber(), 1000 * 60 * 10L);
            throw new BaseException("文件出错");
        } finally {
            redisLock.unlock(key);
        }
    }

    /**
     * 校验文件上传状态
     *
     * @param param
     * @param confFile
     * @return
     * @throws IOException
     */
    public synchronized boolean checkUploadStatus(UploadFile param, File confFile) throws IOException {
        if (!param.isChunkFlag()) {
            return true;
        }
        RandomAccessFile confAccessFile = new RandomAccessFile(confFile, "rw");
        try {
            confAccessFile.setLength(param.getTotalChunks());
            confAccessFile.seek(param.getChunkNumber() - 1);
            confAccessFile.write(Byte.MAX_VALUE);
        } finally {
            IOUtils.closeQuietly(confAccessFile);
        }
        byte[] completeStatusList = FileUtils.readFileToByteArray(confFile);
        for (byte b : completeStatusList) {
            if (b != Byte.MAX_VALUE) {
                return false;
            }
        }
        confFile.delete();
        return true;
    }

    public void writeByteDataToFile(byte[] fileData, File file, UploadFile uploadFile) {
        // 第一步 打开将要写入的文件
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(file, "rw");
            // 第二步 打开通道
            FileChannel fileChannel = raf.getChannel();
            // 第三步 计算偏移量
            long position = (uploadFile.getChunkNumber() - 1) * uploadFile.getChunkSize();
            // 第四步 写入数据
            fileChannel.position(position);
            fileChannel.write(ByteBuffer.wrap(fileData));
            fileChannel.force(true);
            fileChannel.close();
            raf.close();
        } catch (IOException e) {
            throw new BaseException("写入文件失败");
        }
    }

}
