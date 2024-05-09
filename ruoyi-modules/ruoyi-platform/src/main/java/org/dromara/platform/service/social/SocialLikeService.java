package org.dromara.platform.service.social;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.SocialLike;
import org.dromara.platform.domain.bo.SocialLikeBo;
import org.dromara.platform.mapper.SocialLikeMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.platform.domain.vo.social.SocialLikeVo;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * 点赞信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialLikeService implements IBaseService<SocialLike, SocialLikeVo, SocialLikeBo> {

    private final SocialLikeMapper socialLikeMapper;

    @Override
    public IBaseMapper<SocialLike> mapper() {
        return socialLikeMapper;
    }

    @Override
    public LambdaQueryWrapper<SocialLike> buildQueryWrapper(SocialLikeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialLike> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), SocialLike::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), SocialLike::getAppId, bo.getAppIds());
        lqw.eq(bo.getMemberId() != null, SocialLike::getMemberId, bo.getMemberId());
        lqw.eq(bo.getToMemberId() != null, SocialLike::getToMemberId, bo.getToMemberId());
        lqw.eq(bo.getSubjectId() != null, SocialLike::getSubjectId, bo.getSubjectId());
        lqw.eq(StringUtils.isNotBlank(bo.getTargetId()), SocialLike::getTargetId, bo.getTargetId());
        lqw.eq(StringUtils.isNotBlank(bo.getTargetContent()), SocialLike::getTargetContent, bo.getTargetContent());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), SocialLike::getDescription, bo.getDescription());
        return lqw;
    }


}
