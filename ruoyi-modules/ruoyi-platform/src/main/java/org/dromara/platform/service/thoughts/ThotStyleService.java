package org.dromara.platform.service.thoughts;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.platform.domain.thoughts.ThotStyle;
import org.dromara.platform.domain.thoughts.bo.ThotStyleBo;
import org.dromara.platform.domain.thoughts.vo.ThotStyleVo;
import org.dromara.platform.mapper.ThotStyleMapper;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * 样式信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class ThotStyleService implements IBaseService<ThotStyle, ThotStyleVo, ThotStyleBo> {

    private final ThotStyleMapper thotStyleMapper;

    @Override
    public IBaseMapper<ThotStyle> mapper() {
        return thotStyleMapper;
    }

    @Override
    public LambdaQueryWrapper<ThotStyle> buildQueryWrapper(ThotStyleBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ThotStyle> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), ThotStyle::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), ThotStyle::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getStyleCode()), ThotStyle::getStyleCode, bo.getStyleCode());
        lqw.like(StringUtils.isNotBlank(bo.getStyleName()), ThotStyle::getStyleName, bo.getStyleName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ThotStyle::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 修改样式状态
     *
     * @param styleId 样式ID
     * @param status  样式状态
     * @return 结果
     */
    public int updateStatus(Long styleId, String status) {
        return thotStyleMapper.update(null,
            new LambdaUpdateWrapper<ThotStyle>().set(ThotStyle::getStatus, status).eq(ThotStyle::getStyleId, styleId));
    }

}
