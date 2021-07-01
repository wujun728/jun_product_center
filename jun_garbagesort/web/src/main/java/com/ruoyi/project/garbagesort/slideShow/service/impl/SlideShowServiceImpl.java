package com.ruoyi.project.garbagesort.slideShow.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.garbagesort.slideShow.mapper.SlideShowMapper;
import com.ruoyi.project.garbagesort.slideShow.domain.SlideShow;
import com.ruoyi.project.garbagesort.slideShow.service.ISlideShowService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 幻灯片播放Service业务层处理
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
@Service
public class SlideShowServiceImpl implements ISlideShowService 
{
    @Autowired
    private SlideShowMapper slideShowMapper;

    /**
     * 查询幻灯片播放
     * 
     * @param slide 幻灯片播放ID
     * @return 幻灯片播放
     */
    @Override
    public SlideShow selectSlideShowById(Long slide)
    {
        return slideShowMapper.selectSlideShowById(slide);
    }

    /**
     * 查询幻灯片播放列表
     * 
     * @param slideShow 幻灯片播放
     * @return 幻灯片播放
     */
    @Override
    public List<SlideShow> selectSlideShowList(SlideShow slideShow)
    {
        return slideShowMapper.selectSlideShowList(slideShow);
    }

    /**
     * 新增幻灯片播放
     * 
     * @param slideShow 幻灯片播放
     * @return 结果
     */
    @Override
    public int insertSlideShow(SlideShow slideShow)
    {
        return slideShowMapper.insertSlideShow(slideShow);
    }

    /**
     * 修改幻灯片播放
     * 
     * @param slideShow 幻灯片播放
     * @return 结果
     */
    @Override
    public int updateSlideShow(SlideShow slideShow)
    {
        return slideShowMapper.updateSlideShow(slideShow);
    }

    /**
     * 删除幻灯片播放对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSlideShowByIds(String ids)
    {
        return slideShowMapper.deleteSlideShowByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除幻灯片播放信息
     * 
     * @param slide 幻灯片播放ID
     * @return 结果
     */
    @Override
    public int deleteSlideShowById(Long slide)
    {
        return slideShowMapper.deleteSlideShowById(slide);
    }
}
