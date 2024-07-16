package org.dromara.basis.app.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.app.bo.AppExtendBo;
import org.dromara.basis.app.bo.AppInfoBo;
import org.dromara.basis.app.entity.AppInfo;
import org.dromara.basis.app.mapper.AppInfoMapper;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.result.ModifyResult;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
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
public class AppInfoService implements IBaseService<AppInfo, AppInfoBo> {

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

}
