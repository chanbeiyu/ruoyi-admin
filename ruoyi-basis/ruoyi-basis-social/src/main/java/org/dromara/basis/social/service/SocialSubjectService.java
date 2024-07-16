package org.dromara.basis.social.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.social.bo.SocialSubjectBo;
import org.dromara.basis.social.entity.SocialSubject;
import org.dromara.basis.social.mapper.SocialSubjectMapper;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.common.redis.utils.CacheUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 内容主题Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Service
@RequiredArgsConstructor
public class SocialSubjectService implements IBaseService<SocialSubject, SocialSubjectBo> {

    private final SocialSubjectMapper socialSubjectMapper;

    @Override
    public IBaseMapper<SocialSubject> mapper() {
        return socialSubjectMapper;
    }

    @Override
    public LambdaQueryWrapper<SocialSubject> buildQueryWrapper(SocialSubjectBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialSubject> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), SocialSubject::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), SocialSubject::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getSubjectCode()), SocialSubject::getSubjectCode, bo.getSubjectCode());
        lqw.like(StringUtils.isNotBlank(bo.getSubjectName()), SocialSubject::getSubjectName, bo.getSubjectName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), SocialSubject::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 修改主题通知状态
     *
     * @param subjectId 主题ID
     * @param status    状态
     * @return 结果
     */
    public int updateStatus(Long subjectId, String status) {
        return socialSubjectMapper.update(null,
            new LambdaUpdateWrapper<SocialSubject>().set(SocialSubject::getStatus, status)
                .eq(SocialSubject::getSubjectId, subjectId));
    }

}
