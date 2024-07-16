package org.dromara.basis.app.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.app.bo.AppSubscribeBo;
import org.dromara.basis.app.entity.AppSubscribe;
import org.dromara.basis.app.mapper.AppSubscribeMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * App订阅Service业务层处理
 *
 * @author chanbeiyu
 * @date 2024-07-14
 */
@RequiredArgsConstructor
@Service
public class AppSubscribeService implements IBaseService<AppSubscribe, AppSubscribeBo> {

    private final AppSubscribeMapper appSubscribeMapper;


    @Override
    public IBaseMapper<AppSubscribe> mapper() {
        return appSubscribeMapper;
    }

    @Override
    public LambdaQueryWrapper<AppSubscribe> buildQueryWrapper(AppSubscribeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AppSubscribe> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, AppSubscribe::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), AppSubscribe::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getCode()), AppSubscribe::getCode, bo.getCode());
        lqw.eq(StringUtils.isNotBlank(bo.getTitle()), AppSubscribe::getTitle, bo.getTitle());
        lqw.eq(bo.getCycleType() != null, AppSubscribe::getCycleType, bo.getCycleType());
        lqw.eq(StringUtils.isNotBlank(bo.getSupportRenew()), AppSubscribe::getSupportRenew, bo.getSupportRenew());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), AppSubscribe::getStatus, bo.getStatus());
        return lqw;
    }

}
