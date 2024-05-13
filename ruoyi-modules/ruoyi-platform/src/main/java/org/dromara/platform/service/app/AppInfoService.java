package org.dromara.platform.service.app;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.platform.constant.RedisKey;
import org.dromara.platform.domain.app.AppInfo;
import org.dromara.platform.domain.app.bo.AppExtendBo;
import org.dromara.platform.domain.app.bo.AppInfoBo;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.common.redis.utils.CacheUtils;
import org.dromara.platform.domain.app.vo.AppInfoVo;
import org.dromara.platform.mapper.AppInfoMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 应用信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Service
@RequiredArgsConstructor
public class AppInfoService implements IBaseService<AppInfo, AppInfoVo, AppInfoBo> {

    private final AppInfoMapper appInfoMapper;
    private final AppExtendService appExtendService;

    @Override
    public IBaseMapper<AppInfo> mapper() {
        return appInfoMapper;
    }

    @Override
    public LambdaQueryWrapper<AppInfo> buildQueryWrapper(AppInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AppInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), AppInfo::getAppId, bo.getAppId());
        lqw.like(StringUtils.isNotBlank(bo.getAppCode()), AppInfo::getAppCode, bo.getAppCode());
        lqw.like(StringUtils.isNotBlank(bo.getAppName()), AppInfo::getAppName, bo.getAppName());
        lqw.eq(Objects.nonNull(bo.getIsInternal()), AppInfo::getIsInternal, bo.getIsInternal());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), AppInfo::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增应用信息
     */
    @Override
    public Boolean insertByBo(AppInfoBo bo) {
        AppInfo add = MapstructUtils.convert(bo, AppInfo.class);
        boolean flag = appInfoMapper.insert(add) > 0;
        if (flag) {
            bo.setAppId(add.getAppId());
            AppExtendBo extendBo = new AppExtendBo();
            extendBo.setAppId(add.getAppId());
            appExtendService.insertByBo(extendBo);
            RedisUtils.setCacheMapValue(RedisKey.APP_ID_NAME, add.getAppId() + "", bo.getAppName());
            RedisUtils.setCacheMapValue(RedisKey.APP_ID_CODE, add.getAppId() + "", add.getAppCode());
            RedisUtils.setCacheMapValue(RedisKey.APP_CODE_NAME, add.getAppCode(), bo.getAppName());
        }
        return flag;
    }

    /**
     * 修改应用信息
     */
    @Override
    public Boolean updateByBo(AppInfoBo bo) {
        AppInfo update = MapstructUtils.convert(bo, AppInfo.class);
        boolean bool = appInfoMapper.updateById(update) > 0;
        if (bool) {
            RedisUtils.setCacheMapValue(RedisKey.APP_ID_NAME, update.getAppId() + "", update.getAppName());
            RedisUtils.setCacheMapValue(RedisKey.APP_ID_CODE, update.getAppId() + "", update.getAppCode());
            RedisUtils.setCacheMapValue(RedisKey.APP_CODE_NAME, update.getAppCode(), update.getAppName());
        }
        return bool;
    }

    /**
     * 修改APP状态
     *
     * @param appId  APPID
     * @param status 状态
     * @return 结果
     */
    public int updateStatus(Long appId, String status) {
        return appInfoMapper.update(null,
            new LambdaUpdateWrapper<AppInfo>().set(AppInfo::getStatus, status).eq(AppInfo::getAppId, appId));
    }

    /**
     * 批量删除应用信息
     */
    @Override
    public Boolean deleteByIds(Collection<Long> ids) {
        List<AppInfo> appInfos = appInfoMapper.selectBatchIds(ids);
        boolean bool = appInfoMapper.deleteBatchIds(ids) > 0;
        if (bool) {
            appInfos.forEach(o -> {
                RedisUtils.delCacheMapValue(RedisKey.APP_ID_NAME, o.getAppId() + "");
                RedisUtils.delCacheMapValue(RedisKey.APP_ID_CODE, o.getAppCode());
                RedisUtils.delCacheMapValue(RedisKey.APP_CODE_NAME, o.getAppCode());
            });
        }
        return bool;
    }
}
