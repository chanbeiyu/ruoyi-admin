package org.dromara.platform.mapper;

import org.dromara.platform.domain.member.MemberType;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 会员类型信息Mapper接口
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Repository
public interface MemberTypeMapper extends IBaseMapper<MemberType> {

}
