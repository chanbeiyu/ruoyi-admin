package org.dromara.platform.mapper;

import org.dromara.platform.domain.social.SocialFollow;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 关注信息Mapper接口
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Repository
public interface SocialFollowMapper extends IBaseMapper<SocialFollow> {

}
