package org.dromara.basis.member.mapper;


import org.dromara.basis.member.entity.MemberLoginLog;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 系统访问日志情况信息 数据层
 *
 * @author chanbeiyu
 */
@Repository
public interface MemberLoginLogMapper extends IBaseMapper<MemberLoginLog> {

}
