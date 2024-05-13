package org.dromara.platform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dromara.platform.domain.thoughts.ThotThought;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 思绪信息Mapper接口
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */

@Repository
public interface ThotThoughtMapper extends IBaseMapper<ThotThought> {

}
