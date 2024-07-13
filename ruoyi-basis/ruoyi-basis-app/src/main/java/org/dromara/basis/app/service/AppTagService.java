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
import org.dromara.basis.constant.RedisKey;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.common.redis.utils.RedisUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
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
     * 新增标签信息
     */
    @Override
    public Boolean insertByBo(AppTagBo bo) {
        AppTag add = MapstructUtils.convert(bo, AppTag.class);
        if (add == null) {
            return false;
        }

        AppSubject appSubjectVo = appSubjectMapper.selectById(bo.getSubjectId());
        if (appSubjectVo == null) {
            return false;
        }

        add.setAppId(appSubjectVo.getAppId());
        boolean flag = appTagMapper.insert(add) > 0;

        if (flag) {
            bo.setTagId(add.getTagId());
            RedisUtils.setCacheMapValue(RedisKey.APP_TAG_ID_NAME, add.getTagId() + "", bo.getTagName());
            RedisUtils.setCacheMapValue(RedisKey.APP_TAG_CODE_NAME, add.getTagCode(), bo.getTagName());
        }
        return flag;
    }

    /**
     * 修改标签信息
     */
    @Override
    public Boolean updateByBo(AppTagBo bo) {
        AppTag update = MapstructUtils.convert(bo, AppTag.class);
        if (update == null) {
            return false;
        }

        AppSubject appSubject = appSubjectMapper.selectById(bo.getSubjectId());
        if (appSubject == null) {
            return false;
        }
        update.setAppId(appSubject.getAppId());
        boolean bool = appTagMapper.updateById(update) > 0;
        if (bool) {
            RedisUtils.setCacheMapValue(RedisKey.APP_TAG_ID_NAME, update.getTagId() + "", update.getTagName());
            RedisUtils.setCacheMapValue(RedisKey.APP_TAG_CODE_NAME, update.getTagCode(), update.getTagName());
        }
        return bool;
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

    /**
     * 批量删除标签信息
     */
    @Override
    public Boolean  deleteByIds(Collection<Serializable> ids) {
        List<AppTag> socialTags = appTagMapper.selectBatchIds(ids);
        boolean bool = appTagMapper.deleteBatchIds(ids) > 0;
        if (bool) {
            socialTags.forEach(o -> {
                RedisUtils.delCacheMapValue(RedisKey.APP_SUBJECT_ID_NAME, o.getTagId() + "");
                RedisUtils.delCacheMapValue(RedisKey.APP_SUBJECT_CODE_NAME, o.getTagCode());
            });
        }
        return bool;
    }

}
