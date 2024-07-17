package org.dromara.basis.app.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.app.bo.AppExtendBo;
import org.dromara.basis.app.entity.AppExtend;
import org.dromara.basis.app.mapper.AppExtendMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 应用扩展信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class AppExtendService implements IBaseService<AppExtend, AppExtendBo> {

    private final AppExtendMapper appExtendMapper;

    @Override
    public IBaseMapper<AppExtend> mapper() {
        return appExtendMapper;
    }

    @Override
    public LambdaQueryWrapper<AppExtend> buildQueryWrapper(AppExtendBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AppExtend> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getKey()), AppExtend::getKey, bo.getKey());
        lqw.like(StringUtils.isNotBlank(bo.getLabel()), AppExtend::getLabel, bo.getLabel());
        lqw.like(StringUtils.isNotBlank(bo.getVersion()), AppExtend::getVersion, bo.getVersion());
        return lqw;
    }

}
