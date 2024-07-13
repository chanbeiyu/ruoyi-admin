package org.dromara.basis.apptw.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.apptw.bo.ApptwQuotaBo;
import org.dromara.basis.apptw.entity.ApptwQuota;
import org.dromara.basis.apptw.mapper.ApptwQuotaMapper;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 目标限额Service业务层处理
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@RequiredArgsConstructor
@Service
public class ApptwQuotaService implements IBaseService<ApptwQuota, ApptwQuotaBo> {

    private final ApptwQuotaMapper apptwQuotaMapper;

    @Override
    public IBaseMapper<ApptwQuota> mapper() {
        return apptwQuotaMapper;
    }

    @Override
    public LambdaQueryWrapper<ApptwQuota> buildQueryWrapper(ApptwQuotaBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ApptwQuota> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getMemberId() != null, ApptwQuota::getMemberId, bo.getMemberId());
        lqw.eq(bo.getMonth() != null, ApptwQuota::getMonth, bo.getMonth());
        return lqw;
    }

}
