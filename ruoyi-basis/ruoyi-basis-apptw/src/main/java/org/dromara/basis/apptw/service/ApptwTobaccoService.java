package org.dromara.basis.apptw.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.apptw.bo.ApptwTobaccoBo;
import org.dromara.basis.apptw.entity.ApptwTobacco;
import org.dromara.basis.apptw.mapper.ApptwTobaccoMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 香烟信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@RequiredArgsConstructor
@Service
public class ApptwTobaccoService implements IBaseService<ApptwTobacco, ApptwTobaccoBo> {

    private final ApptwTobaccoMapper apptwTobaccoMapper;

    @Override
    public IBaseMapper<ApptwTobacco> mapper() {
        return apptwTobaccoMapper;
    }

    @Override
    public LambdaQueryWrapper<ApptwTobacco> buildQueryWrapper(ApptwTobaccoBo bo) {
        LambdaQueryWrapper<ApptwTobacco> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getBrandId() != null, ApptwTobacco::getBrandId, bo.getBrandId());
        lqw.eq(bo.getProductType() != null, ApptwTobacco::getProductType, bo.getProductType());
        lqw.like(StringUtils.isNotBlank(bo.getFullName()), ApptwTobacco::getFullName, bo.getFullName());
        lqw.like(StringUtils.isNotBlank(bo.getFullEnname()), ApptwTobacco::getFullEnname, bo.getFullEnname());
        lqw.like(StringUtils.isNotBlank(bo.getBoxBarcode()), ApptwTobacco::getBoxBarcode, bo.getBoxBarcode());
        lqw.like(StringUtils.isNotBlank(bo.getStripBarcode()), ApptwTobacco::getStripBarcode, bo.getStripBarcode());
        return lqw;
    }

}
