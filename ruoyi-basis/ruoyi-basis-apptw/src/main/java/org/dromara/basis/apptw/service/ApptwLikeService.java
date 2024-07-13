package org.dromara.basis.apptw.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.apptw.bo.ApptwLikeBo;
import org.dromara.basis.apptw.entity.ApptwLike;
import org.dromara.basis.apptw.mapper.ApptwLikeMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 喜好品类Service业务层处理
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@RequiredArgsConstructor
@Service
public class ApptwLikeService implements IBaseService<ApptwLike, ApptwLikeBo> {

    private final ApptwLikeMapper apptwBrandMapper;

    @Override
    public IBaseMapper<ApptwLike> mapper() {
        return apptwBrandMapper;
    }

    @Override
    public LambdaQueryWrapper<ApptwLike> buildQueryWrapper(ApptwLikeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ApptwLike> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getTobaccoId() != null, ApptwLike::getTobaccoId, bo.getTobaccoId());
        lqw.eq(bo.getMemberId() != null, ApptwLike::getMemberId, bo.getMemberId());
        return lqw;
    }

}
