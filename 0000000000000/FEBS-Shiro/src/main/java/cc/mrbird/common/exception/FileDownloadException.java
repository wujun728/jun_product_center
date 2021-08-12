package cc.mrbird.common.exception;

/**
 * 文件下载异常
 */
public class FileDownloadException  extends Exception {

    private static final long serialVersionUID = -3608667856397125671L;

    public FileDownloadException(String message) {
        super(message);
    }
}