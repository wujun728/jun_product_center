package me.wuwenbin.noteblogv5.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.wuwenbin.noteblogv5.mapper.NoteMapper;
import me.wuwenbin.noteblogv5.model.entity.Note;
import me.wuwenbin.noteblogv5.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wuwen
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements NoteService {
}
