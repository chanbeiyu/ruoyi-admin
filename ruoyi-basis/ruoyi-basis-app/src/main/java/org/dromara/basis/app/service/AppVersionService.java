package org.dromara.basis.app.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.app.bo.AppVersionBo;
import org.dromara.basis.app.entity.AppInfo;
import org.dromara.basis.app.entity.AppVersion;
import org.dromara.basis.app.mapper.AppVersionMapper;
import org.dromara.common.core.service.DictService;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 应用版本信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class AppVersionService implements IBaseService<AppVersion, AppVersionBo> {

    private final AppVersionMapper appVersionMapper;

    @Override
    public IBaseMapper<AppVersion> mapper() {
        return appVersionMapper;
    }

    @Override
    public LambdaQueryWrapper<AppVersion> buildQueryWrapper(AppVersionBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AppVersion> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, AppVersion::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), AppVersion::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getShowVersion()), AppVersion::getShowVersion, bo.getShowVersion());
        lqw.eq(StringUtils.isNotBlank(bo.getBuildVersion()), AppVersion::getBuildVersion, bo.getBuildVersion());
        lqw.eq(bo.getForced() != null, AppVersion::getForced, bo.getForced());
        lqw.eq(bo.getPublishTime() != null, AppVersion::getPublishTime, bo.getPublishTime());
        return lqw;
    }

     /**
     * 修改版本状态
     *
     * @param versionId  APPID
     * @param status 状态
     * @return 结果
     */
    public int updateStatus(Long versionId, String status) {
        AppVersion appVersion = appVersionMapper.selectById(versionId);
        if(appVersion != null) {
            AppVersionBo versionBo = new AppVersionBo();
            versionBo.setStatus("Y");
            versionBo.setAppId(appVersion.getAppId());
            List<AppVersion> appVersions = appVersionMapper.selectList(buildQueryWrapper(versionBo));
            if(appVersions != null) {
                appVersions.forEach(o -> {
                    if(versionId.equals(o.getVersionId())) {
                        o.setStatus(status);
                    } else {
                        o.setStatus("N");
                    }
                });
            }
            appVersionMapper.updateBatchById(appVersions);
        }
        return 1;
    }

}
