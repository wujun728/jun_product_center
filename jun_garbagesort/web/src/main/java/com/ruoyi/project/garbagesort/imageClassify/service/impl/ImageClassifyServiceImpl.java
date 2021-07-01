package com.ruoyi.project.garbagesort.imageClassify.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.garbagesort.imageClassify.mapper.ImageClassifyMapper;
import com.ruoyi.project.garbagesort.imageClassify.domain.ImageClassify;
import com.ruoyi.project.garbagesort.imageClassify.service.IImageClassifyService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 图像识别记录Service业务层处理
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
@Service
public class ImageClassifyServiceImpl implements IImageClassifyService 
{
    @Autowired
    private ImageClassifyMapper imageClassifyMapper;

    /**
     * 查询图像识别记录
     * 
     * @param id 图像识别记录ID
     * @return 图像识别记录
     */
    @Override
    public ImageClassify selectImageClassifyById(Long id)
    {
        return imageClassifyMapper.selectImageClassifyById(id);
    }

    /**
     * 查询图像识别记录列表
     * 
     * @param imageClassify 图像识别记录
     * @return 图像识别记录
     */
    @Override
    public List<ImageClassify> selectImageClassifyList(ImageClassify imageClassify)
    {
        return imageClassifyMapper.selectImageClassifyList(imageClassify);
    }

    /**
     * 新增图像识别记录
     * 
     * @param imageClassify 图像识别记录
     * @return 结果
     */
    @Override
    public int insertImageClassify(ImageClassify imageClassify)
    {
        return imageClassifyMapper.insertImageClassify(imageClassify);
    }

    /**
     * 修改图像识别记录
     * 
     * @param imageClassify 图像识别记录
     * @return 结果
     */
    @Override
    public int updateImageClassify(ImageClassify imageClassify)
    {
        return imageClassifyMapper.updateImageClassify(imageClassify);
    }

    /**
     * 删除图像识别记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImageClassifyByIds(String ids)
    {
        return imageClassifyMapper.deleteImageClassifyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除图像识别记录信息
     * 
     * @param id 图像识别记录ID
     * @return 结果
     */
    @Override
    public int deleteImageClassifyById(Long id)
    {
        return imageClassifyMapper.deleteImageClassifyById(id);
    }
}
