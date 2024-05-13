package org.dromara.platform.service.app;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.app.AppAdvice;
import org.dromara.platform.domain.app.bo.AppAdviceBo;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.platform.domain.app.vo.AppAdviceVo;
import org.dromara.platform.mapper.AppAdviceMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 意见反馈信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Service
@RequiredArgsConstructor
public class AppAdviceService implements IBaseService<AppAdvice, AppAdviceVo, AppAdviceBo> {

    private final AppAdviceMapper appAdviceMapper;

    @Override
    public IBaseMapper<AppAdvice> mapper() {
        return appAdviceMapper;
    }

    @Override
    public LambdaQueryWrapper<AppAdvice> buildQueryWrapper(AppAdviceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AppAdvice> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, AppAdvice::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), AppAdvice::getAppId, bo.getAppIds());
        lqw.eq(bo.getMemberId() != null, AppAdvice::getMemberId, bo.getMemberId());
        lqw.eq(StringUtils.isNotBlank(bo.getContact()), AppAdvice::getContact, bo.getContact());
        lqw.eq(StringUtils.isNotBlank(bo.getContent()), AppAdvice::getContent, bo.getContent());
        lqw.eq(bo.getCreateTime() != null, AppAdvice::getCreateTime, bo.getCreateTime());
        return lqw;
    }

}
