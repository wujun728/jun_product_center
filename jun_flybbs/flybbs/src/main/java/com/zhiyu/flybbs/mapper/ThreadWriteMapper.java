package com.zhiyu.flybbs.mapper;

import com.zhiyu.flybbs.domain.Thread;
import org.springframework.stereotype.Repository;

/**
 * @author zhiyu
 */
@Repository
public interface ThreadWriteMapper {

    int insert(Thread thread);

    int update(Thread thread);
}
