package com.ruoyi.project.garbagesort.slideShow.mapper;

import java.util.List;
import com.ruoyi.project.garbagesort.slideShow.domain.SlideShow;

/**
 * 幻灯片播放Mapper接口
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
public interface SlideShowMapper 
{
    /**
     * 查询幻灯片播放
     * 
     * @param slide 幻灯片播放ID
     * @return 幻灯片播放
     */
    public SlideShow selectSlideShowById(Long slide);

    /**
     * 查询幻灯片播放列表
     * 
     * @param slideShow 幻灯片播放
     * @return 幻灯片播放集合
     */
    public List<SlideShow> selectSlideShowList(SlideShow slideShow);

    /**
     * 新增幻灯片播放
     * 
     * @param slideShow 幻灯片播放
     * @return 结果
     */
    public int insertSlideShow(SlideShow slideShow);

    /**
     * 修改幻灯片播放
     * 
     * @param slideShow 幻灯片播放
     * @return 结果
     */
    public int updateSlideShow(SlideShow slideShow);

    /**
     * 删除幻灯片播放
     * 
     * @param slide 幻灯片播放ID
     * @return 结果
     */
    public int deleteSlideShowById(Long slide);

    /**
     * 批量删除幻灯片播放
     * 
     * @param slides 需要删除的数据ID
     * @return 结果
     */
    public int deleteSlideShowByIds(String[] slides);
}
