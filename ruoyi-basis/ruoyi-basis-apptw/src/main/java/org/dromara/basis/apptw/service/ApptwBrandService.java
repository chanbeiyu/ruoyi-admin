package org.dromara.basis.apptw.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.apptw.bo.ApptwBrandBo;
import org.dromara.basis.apptw.entity.ApptwBrand;
import org.dromara.basis.apptw.mapper.ApptwBrandMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 香烟品牌Service业务层处理
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@RequiredArgsConstructor
@Service
public class ApptwBrandService implements IBaseService<ApptwBrand, ApptwBrandBo> {

    private final ApptwBrandMapper apptwBrandMapper;

    @Override
    public IBaseMapper<ApptwBrand> mapper() {
        return apptwBrandMapper;
    }


    @Override
    public LambdaQueryWrapper<ApptwBrand> buildQueryWrapper(ApptwBrandBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ApptwBrand> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), ApptwBrand::getName, bo.getName());
        lqw.like(StringUtils.isNotBlank(bo.getEnname()), ApptwBrand::getEnname, bo.getEnname());
        lqw.eq(StringUtils.isNotBlank(bo.getType()), ApptwBrand::getType, bo.getType());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), ApptwBrand::getDescription, bo.getDescription());
        return lqw;
    }

}
