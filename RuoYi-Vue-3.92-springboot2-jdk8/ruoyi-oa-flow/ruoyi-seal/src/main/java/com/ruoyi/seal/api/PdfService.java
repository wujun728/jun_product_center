package com.ruoyi.seal.api;


import com.ruoyi.seal.utils.pdf.param.CompanyPdfAcroFields;

/**
 * pdf服务
 * @Author wocurr.com
 */
public interface PdfService {

    /**
     * 根据模板生成pdf
     * @param fileName 文件名
     * @param templateFileId 模板文件id
     * @param acroFields 表单域字段
     * @return 返回生成的文件fileId
     */
    public String createByTemplate(String fileName, String templateFileId, CompanyPdfAcroFields acroFields);

    /**
     * 水印
     * @param fileId
     * @param fileName
     * @param waterContent
     * @return 返回生成水印的文件fileId
     */
    public String waterMark(String fileId, String fileName, String waterContent);

    /**
     * 骑缝章
     * @param fileId 文件id
     * @param fileName 文件名
     * @param sealImgFileId 印章图片文件id
     * @return
     */
    public String seamSeal(String fileId, String fileName, String sealImgFileId);

    /**
     * 正文盖章
     * @param fileId 文件id
     * @param fileName 文件名
     * @param sealImgFileId 印章图片文件id
     * @param position 盖章位置，指同一行的左章或者右章
     * @param sealPage 印章所在页码
     * @return
     */
    public String stamp(String fileId, String fileName, String sealImgFileId, String position, int sealPage);

    /**
     * 正文盖章
     * @param fileId 文件ID
     * @param fileName 文件名
     * @param sealImgFileId 印章图片文件id
     * @param positionX 印章x位置
     * @param positionY 印章y位置
     * @param sealPage 印章所在页码
     * @param isUsePreviewUrl 是否使用预览文件进行盖章
     * @return
     */
    public String stamp(String fileId, String fileName, String sealImgFileId, float positionX, float positionY, int sealPage, boolean isUsePreviewUrl);

    /**
     * 盖章（适用于单方盖章）<br>
     *  举例：<br>
     *      1、合同发起方先创建好合同；<br>
     *      2、合同发起方调用此方法进行盖章；<br>
     * @param fileId 文件id
     * @param fileName 文件名
     * @param sealImgFileId 印章图片文件id
     * @param position 盖章位置，指同一行的左章或者右章
     * @param sealPage 印章所在页码
     * @param isSeamSeal 是否添加骑缝章
     * @param isWatermark 是否添加水印
     * @param waterContent 水印内容
     * @return 返回盖章后的文件fileId
     */
    public String stamp(String fileId, String fileName, String sealImgFileId, String position, int sealPage, boolean isSeamSeal, boolean isWatermark, String waterContent);

    /**
     * 盖章 + 水印 + 骑缝章（此方法是最终盖章，会生成甲乙双方的骑缝章、印章）<br>
     * 举例：<br>
     *      1、合同发起方先创建合同；<br>
     *      2、合同发起方先调用（stampSeamAndWaterMark方法）进行盖章 + 水印 + 骑缝章（便于接收方看到发起方已盖章处理）；<br>
     *      3、合同接收方调用此方法进行最终盖章（重新生成一份包含双方印章的文件，且不影响证书痕迹）；<br>
     * @param fileId 文件id
     * @param fileName 文件名
     * @param partASealFileId 甲方印章文件id
     * @param partBSealFileId 乙方印章文件id
     * @param sealPage 印章所在页码
     * @param isSeamSeal 是否添加骑缝章
     * @param isWatermark 是否添加水印
     * @param waterContent 水印内容
     * @return 返回盖章后的文件fileId
     */
    public String stampBothFinal(String fileId, String fileName, String partASealFileId, String partBSealFileId, int sealPage, boolean isSeamSeal, boolean isWatermark, String waterContent);

    /**
     * 签名
     * @param fileId 文件id
     * @param fileName 文件名
     * @param signBase64Img 签名图片base64
     * @param keyword 签名关键字，控制签名图片最终位置
     * @return
     */
    public String signature(String fileId, String fileName, String signBase64Img, String keyword);

    /**
     * 设置文件打开密码
     * @param fileId 文件id
     * @param fileName 文件名
     * @param ownerPwd 拥有者密码
     * @param userPwd 用户密码
     * @return
     */
    public String encrypt(String fileId, String fileName, String ownerPwd, String userPwd);
}
