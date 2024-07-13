package org.dromara.basis.social.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.social.bo.SocialCommentBo;
import org.dromara.basis.social.entity.SocialComment;
import org.dromara.basis.social.mapper.SocialCommentMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * 评论信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialCommentService implements IBaseService<SocialComment, SocialCommentBo> {

    private final SocialCommentMapper socialCommentMapper;

    @Override
    public IBaseMapper<SocialComment> mapper() {
        return socialCommentMapper;
    }

    @Override
    public LambdaQueryWrapper<SocialComment> buildQueryWrapper(SocialCommentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialComment> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCommentPid() != null, SocialComment::getCommentPid, bo.getCommentPid());
        lqw.eq(Objects.nonNull(bo.getAppId()), SocialComment::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), SocialComment::getAppId, bo.getAppIds());
        lqw.eq(bo.getMemberId() != null, SocialComment::getMemberId, bo.getMemberId());
        lqw.eq(bo.getToMemberId() != null, SocialComment::getToMemberId, bo.getToMemberId());
        lqw.eq(bo.getSubjectId() != null, SocialComment::getSubjectId, bo.getSubjectId());
        lqw.eq(StringUtils.isNotBlank(bo.getTargetId()), SocialComment::getTargetId, bo.getTargetId());
        lqw.eq(StringUtils.isNotBlank(bo.getTargetContent()), SocialComment::getTargetContent, bo.getTargetContent());
        lqw.eq(StringUtils.isNotBlank(bo.getCommentTitle()), SocialComment::getCommentTitle, bo.getCommentTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getCommentContnet()), SocialComment::getCommentContnet,
            bo.getCommentContnet());
        lqw.eq(bo.getLikeNum() != null, SocialComment::getLikeNum, bo.getLikeNum());
        lqw.eq(bo.getAllowComment() != null, SocialComment::getAllowComment, bo.getAllowComment());
        lqw.eq(bo.getStatus() != null, SocialComment::getStatus, bo.getStatus());
        lqw.eq(bo.getDeleteTime() != null, SocialComment::getDeleteTime, bo.getDeleteTime());
        return lqw;
    }

}
