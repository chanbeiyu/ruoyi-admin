package org.dromara.platform.mapper;

import org.dromara.platform.domain.social.SocialComment;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 评论信息Mapper接口
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Repository
public interface SocialCommentMapper extends IBaseMapper<SocialComment> {

}
