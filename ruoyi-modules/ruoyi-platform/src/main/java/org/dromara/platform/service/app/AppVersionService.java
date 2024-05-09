package org.dromara.platform.service.app;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.AppVersion;
import org.dromara.platform.domain.bo.AppVersionBo;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.platform.domain.vo.app.AppVersionVo;
import org.dromara.platform.mapper.AppVersionMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 应用版本信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class AppVersionService implements IBaseService<AppVersion, AppVersionVo, AppVersionBo> {

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
        lqw.eq(StringUtils.isNotBlank(bo.getVersion()), AppVersion::getVersion, bo.getVersion());
        lqw.eq(StringUtils.isNotBlank(bo.getBuildVersion()), AppVersion::getBuildVersion, bo.getBuildVersion());
        lqw.eq(bo.getForced() != null, AppVersion::getForced, bo.getForced());
        lqw.eq(bo.getPublishTime() != null, AppVersion::getPublishTime, bo.getPublishTime());
        return lqw;
    }


}
