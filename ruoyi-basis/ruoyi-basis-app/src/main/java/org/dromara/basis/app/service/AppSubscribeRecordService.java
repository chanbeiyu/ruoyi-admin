package org.dromara.basis.app.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.app.bo.AppSubscribeRecordBo;
import org.dromara.basis.app.entity.AppSubscribeRecord;
import org.dromara.basis.app.mapper.AppSubscribeRecordMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * App订阅记录Service业务层处理
 *
 * @author chanbeiyu
 * @date 2024-07-14
 */
@Service
@RequiredArgsConstructor
public class AppSubscribeRecordService implements IBaseService<AppSubscribeRecord, AppSubscribeRecordBo> {

    private final AppSubscribeRecordMapper appSubscribeRecordMapper;

    @Override
    public IBaseMapper<AppSubscribeRecord> mapper() {
        return appSubscribeRecordMapper;
    }

    @Override
    public LambdaQueryWrapper<AppSubscribeRecord> buildQueryWrapper(AppSubscribeRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AppSubscribeRecord> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, AppSubscribeRecord::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), AppSubscribeRecord::getAppId, bo.getAppIds());
        lqw.eq(bo.getSubscribeId() != null, AppSubscribeRecord::getSubscribeId, bo.getSubscribeId());
        lqw.eq(bo.getMemberId() != null, AppSubscribeRecord::getMemberId, bo.getMemberId());
        lqw.like(bo.getOrderId() != null, AppSubscribeRecord::getOrderId, bo.getOrderId());
        lqw.between(params.get("beginStarTime") != null && params.get("endStarTime") != null,
            AppSubscribeRecord::getStarTime, params.get("beginStarTime"), params.get("endStarTime"));
        lqw.between(params.get("beginEndTime") != null && params.get("endEndTime") != null,
            AppSubscribeRecord::getEndTime, params.get("beginEndTime"), params.get("endEndTime"));
        lqw.eq(StringUtils.isNotBlank(bo.getRenewEnable()), AppSubscribeRecord::getRenewEnable, bo.getRenewEnable());
        lqw.like(bo.getRenewRecordId() != null, AppSubscribeRecord::getRenewRecordId, bo.getRenewRecordId());
        lqw.eq(bo.getStatus() != null, AppSubscribeRecord::getStatus, bo.getStatus());
        return lqw;
    }


}
