package org.dromara.basis.apptw.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.apptw.bo.ApptwSmokeBo;
import org.dromara.basis.apptw.entity.ApptwSmoke;
import org.dromara.basis.apptw.mapper.ApptwSmokeMapper;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 抽烟记录Service业务层处理
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@RequiredArgsConstructor
@Service
public class ApptwSmokeService implements IBaseService<ApptwSmoke, ApptwSmokeBo> {

    private final ApptwSmokeMapper apptwSmokeMapper;

    @Override
    public IBaseMapper<ApptwSmoke> mapper() {
        return apptwSmokeMapper;
    }

    @Override
    public LambdaQueryWrapper<ApptwSmoke> buildQueryWrapper(ApptwSmokeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ApptwSmoke> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getMemberId() != null, ApptwSmoke::getMemberId, bo.getMemberId());
        lqw.between(params.get("beginDateTime") != null && params.get("endDateTime") != null,
            ApptwSmoke::getDateTime, params.get("beginDateTime"), params.get("endDateTime"));
        return lqw;
    }

}
