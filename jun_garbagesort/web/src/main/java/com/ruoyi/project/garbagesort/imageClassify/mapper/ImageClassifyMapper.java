package com.ruoyi.project.garbagesort.imageClassify.mapper;

import java.util.List;
import com.ruoyi.project.garbagesort.imageClassify.domain.ImageClassify;

/**
 * 图像识别记录Mapper接口
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
public interface ImageClassifyMapper 
{
    /**
     * 查询图像识别记录
     * 
     * @param id 图像识别记录ID
     * @return 图像识别记录
     */
    public ImageClassify selectImageClassifyById(Long id);

    /**
     * 查询图像识别记录列表
     * 
     * @param imageClassify 图像识别记录
     * @return 图像识别记录集合
     */
    public List<ImageClassify> selectImageClassifyList(ImageClassify imageClassify);

    /**
     * 新增图像识别记录
     * 
     * @param imageClassify 图像识别记录
     * @return 结果
     */
    public int insertImageClassify(ImageClassify imageClassify);

    /**
     * 修改图像识别记录
     * 
     * @param imageClassify 图像识别记录
     * @return 结果
     */
    public int updateImageClassify(ImageClassify imageClassify);

    /**
     * 删除图像识别记录
     * 
     * @param id 图像识别记录ID
     * @return 结果
     */
    public int deleteImageClassifyById(Long id);

    /**
     * 批量删除图像识别记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImageClassifyByIds(String[] ids);
}
