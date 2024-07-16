package org.dromara.basis.social.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.social.bo.SocialTagBo;
import org.dromara.basis.social.entity.SocialSubject;
import org.dromara.basis.social.entity.SocialTag;
import org.dromara.basis.social.mapper.SocialSubjectMapper;
import org.dromara.basis.social.mapper.SocialTagMapper;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.result.ModifyResult;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * 标签信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialTagService implements IBaseService<SocialTag, SocialTagBo> {

    private final SocialTagMapper socialTagMapper;
    private final SocialSubjectMapper socialSubjectMapper;
    private final SocialSubjectService socialSubjectService;

    @Override
    public IBaseMapper<SocialTag> mapper() {
        return socialTagMapper;
    }

    @Override
    public LambdaQueryWrapper<SocialTag> buildQueryWrapper(SocialTagBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialTag> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), SocialTag::getAppId, bo.getAppId());
        lqw.eq(Objects.nonNull(bo.getSubjectId()), SocialTag::getSubjectId, bo.getSubjectId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getSubjectIds()), SocialTag::getSubjectId, bo.getSubjectIds());
        lqw.like(StringUtils.isNotBlank(bo.getTagCode()), SocialTag::getTagCode, bo.getTagCode());
        lqw.like(StringUtils.isNotBlank(bo.getTagName()), SocialTag::getTagName, bo.getTagName());
        lqw.eq(StringUtils.isNotBlank(bo.getTagType()), SocialTag::getTagType, bo.getTagType());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), SocialTag::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 修改标签通知状态
     *
     * @param tagId  标签ID
     * @param status 状态
     * @return 结果
     */
    public int updateStatus(Long tagId, String status) {
        return socialTagMapper.update(null,
            new LambdaUpdateWrapper<SocialTag>().set(SocialTag::getStatus, status).eq(SocialTag::getTagId, tagId));
    }

}
