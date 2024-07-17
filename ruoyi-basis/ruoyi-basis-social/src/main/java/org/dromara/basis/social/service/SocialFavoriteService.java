package org.dromara.basis.social.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.social.bo.SocialFavoriteBo;
import org.dromara.basis.social.entity.SocialFavorite;
import org.dromara.basis.social.mapper.SocialFavoriteMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * 收藏信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Service
@RequiredArgsConstructor
public class SocialFavoriteService implements IBaseService<SocialFavorite, SocialFavoriteBo> {

    private final SocialFavoriteMapper socialFavoriteMapper;

    @Override
    public IBaseMapper<SocialFavorite> mapper() {
        return socialFavoriteMapper;
    }

    @Override
    public LambdaQueryWrapper<SocialFavorite> buildQueryWrapper(SocialFavoriteBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialFavorite> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getMemberId() != null, SocialFavorite::getMemberId, bo.getMemberId());
        lqw.eq(bo.getToMemberId() != null, SocialFavorite::getToMemberId, bo.getToMemberId());
        lqw.eq(bo.getSubjectId() != null, SocialFavorite::getSubjectId, bo.getSubjectId());
        lqw.eq(StringUtils.isNotBlank(bo.getTargetId()), SocialFavorite::getTargetId, bo.getTargetId());
        lqw.eq(StringUtils.isNotBlank(bo.getTargetContent()), SocialFavorite::getTargetContent, bo.getTargetContent());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), SocialFavorite::getDescription, bo.getDescription());
        return lqw;
    }

}
