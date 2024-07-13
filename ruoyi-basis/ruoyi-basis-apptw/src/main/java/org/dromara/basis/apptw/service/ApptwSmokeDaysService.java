package org.dromara.basis.apptw.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.apptw.bo.ApptwSmokeDaysBo;
import org.dromara.basis.apptw.entity.ApptwSmoke;
import org.dromara.basis.apptw.entity.ApptwSmokeDays;
import org.dromara.basis.apptw.mapper.ApptwSmokeDaysMapper;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 日抽烟数据Service业务层处理
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@RequiredArgsConstructor
@Service
public class ApptwSmokeDaysService implements IBaseService<ApptwSmokeDays, ApptwSmokeDaysBo> {

    private final ApptwSmokeDaysMapper apptwSmokeDaysMapper;

    @Override
    public IBaseMapper<ApptwSmokeDays> mapper() {
        return apptwSmokeDaysMapper;
    }

    @Override
    public LambdaQueryWrapper<ApptwSmokeDays> buildQueryWrapper(ApptwSmokeDaysBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ApptwSmokeDays> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getMemberId() != null, ApptwSmokeDays::getMemberId, bo.getMemberId());
        lqw.between(params.get("beginDate") != null && params.get("endDate") != null,
            ApptwSmokeDays::getDay, params.get("beginDate"), params.get("endDate"));
        return lqw;
    }

}
