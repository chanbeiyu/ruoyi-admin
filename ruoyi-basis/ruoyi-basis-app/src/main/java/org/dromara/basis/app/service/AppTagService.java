package org.dromara.basis.app.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.app.bo.AppTagBo;
import org.dromara.basis.app.entity.AppSubject;
import org.dromara.basis.app.entity.AppTag;
import org.dromara.basis.app.mapper.AppSubjectMapper;
import org.dromara.basis.app.mapper.AppTagMapper;
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
public class AppTagService implements IBaseService<AppTag, AppTagBo> {

    private final AppTagMapper appTagMapper;
    private final AppSubjectMapper appSubjectMapper;

    @Override
    public IBaseMapper<AppTag> mapper() {
        return appTagMapper;
    }

    @Override
    public LambdaQueryWrapper<AppTag> buildQueryWrapper(AppTagBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AppTag> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), AppTag::getAppId, bo.getAppId());
        lqw.eq(Objects.nonNull(bo.getSubjectId()), AppTag::getSubjectId, bo.getSubjectId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getSubjectIds()), AppTag::getSubjectId, bo.getSubjectIds());
        lqw.like(StringUtils.isNotBlank(bo.getTagCode()), AppTag::getTagCode, bo.getTagCode());
        lqw.like(StringUtils.isNotBlank(bo.getTagName()), AppTag::getTagName, bo.getTagName());
        lqw.eq(StringUtils.isNotBlank(bo.getTagType()), AppTag::getTagType, bo.getTagType());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), AppTag::getStatus, bo.getStatus());
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
        return appTagMapper.update(null,
            new LambdaUpdateWrapper<AppTag>().set(AppTag::getStatus, status).eq(AppTag::getTagId, tagId));
    }

}
