package org.dromara.basis.app.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.app.bo.AppSubjectBo;
import org.dromara.basis.app.entity.AppSubject;
import org.dromara.basis.app.mapper.AppSubjectMapper;
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
 * 内容主题Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Service
@RequiredArgsConstructor
public class AppSubjectService implements IBaseService<AppSubject, AppSubjectBo> {

    private final AppSubjectMapper appSubjectMapper;

    @Override
    public IBaseMapper<AppSubject> mapper() {
        return appSubjectMapper;
    }

    @Override
    public LambdaQueryWrapper<AppSubject> buildQueryWrapper(AppSubjectBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AppSubject> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), AppSubject::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), AppSubject::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getSubjectCode()), AppSubject::getSubjectCode, bo.getSubjectCode());
        lqw.like(StringUtils.isNotBlank(bo.getSubjectName()), AppSubject::getSubjectName, bo.getSubjectName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), AppSubject::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增内容主题
     */
    @Override
    public Boolean insertByBo(AppSubjectBo bo) {
        AppSubject add = MapstructUtils.convert(bo, AppSubject.class);
        boolean flag = appSubjectMapper.insert(add) > 0;
        if (flag) {
            bo.setSubjectId(add.getSubjectId());
            RedisUtils.setCacheMapValue(RedisKey.APP_SUBJECT_ID_CODE, add.getSubjectId() + "", add.getSubjectCode());
            RedisUtils.setCacheMapValue(RedisKey.APP_SUBJECT_ID_NAME, add.getSubjectId() + "", bo.getSubjectName());
            RedisUtils.setCacheMapValue(RedisKey.APP_SUBJECT_CODE_NAME, add.getSubjectCode(), bo.getSubjectName());
        }
        return flag;
    }

    /**
     * 修改内容主题
     */
    @Override
    public Boolean updateByBo(AppSubjectBo bo) {
        AppSubject update = MapstructUtils.convert(bo, AppSubject.class);
        boolean bool = appSubjectMapper.updateById(update) > 0;
        if (bool) {
            RedisUtils.setCacheMapValue(RedisKey.APP_SUBJECT_ID_CODE, update.getSubjectId() + "", update.getSubjectCode());
            RedisUtils.setCacheMapValue(RedisKey.APP_SUBJECT_ID_NAME, update.getSubjectId() + "", update.getSubjectName());
            RedisUtils.setCacheMapValue(RedisKey.APP_SUBJECT_CODE_NAME, update.getSubjectCode(), update.getSubjectName());
        }
        return bool;
    }

    /**
     * 修改主题通知状态
     *
     * @param subjectId 主题ID
     * @param status    状态
     * @return 结果
     */
    public int updateStatus(Long subjectId, String status) {
        return appSubjectMapper.update(null,
            new LambdaUpdateWrapper<AppSubject>().set(AppSubject::getStatus, status)
                .eq(AppSubject::getSubjectId, subjectId));
    }

    /**
     * 批量删除内容主题
     */
    @Override
    public Boolean deleteByIds(Collection<Serializable> ids) {
        List<AppSubject> appSubjects = appSubjectMapper.selectBatchIds(ids);
        boolean bool = appSubjectMapper.deleteBatchIds(ids) > 0;
        if (bool) {
            appSubjects.forEach(o -> {
                RedisUtils.delCacheMapValue(RedisKey.APP_SUBJECT_ID_CODE, o.getSubjectId() + "");
                RedisUtils.delCacheMapValue(RedisKey.APP_SUBJECT_ID_NAME, o.getSubjectId() + "");
                RedisUtils.delCacheMapValue(RedisKey.APP_SUBJECT_CODE_NAME, o.getSubjectCode());
            });
        }
        return bool;
    }
}
