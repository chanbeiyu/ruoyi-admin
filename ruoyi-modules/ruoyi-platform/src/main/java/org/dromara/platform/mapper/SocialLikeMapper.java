package org.dromara.platform.mapper;

import org.dromara.platform.domain.social.SocialLike;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 点赞信息Mapper接口
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Repository
public interface SocialLikeMapper extends IBaseMapper<SocialLike> {

}
