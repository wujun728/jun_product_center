package com.platform.validator.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
