package me.wuwenbin.noteblogv5.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.wuwenbin.noteblogv5.annotation.MybatisMapper;
import me.wuwenbin.noteblogv5.model.entity.Note;

/**
 * @author wuwen
 */
@MybatisMapper
public interface NoteMapper extends BaseMapper<Note> {
}
