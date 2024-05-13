package org.dromara.platform.service.thoughts;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.platform.constant.DataStatus;
import org.dromara.platform.domain.thoughts.ThotThought;
import org.dromara.platform.domain.thoughts.bo.ThotThoughtBo;
import org.dromara.platform.domain.thoughts.vo.ThotThoughtVo;
import org.dromara.platform.mapper.ThotThoughtMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 思绪信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class ThotThoughtService implements IBaseService<ThotThought, ThotThoughtVo, ThotThoughtBo> {

    private final ThotThoughtMapper thotThoughtMapper;

    @Override
    public IBaseMapper<ThotThought> mapper() {
        return thotThoughtMapper;
    }

    /**
     * 查询思绪信息列表
     */
    @Override
    public TableDataInfo<ThotThoughtVo> queryPageList(ThotThoughtBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ThotThought> lqw = buildQueryWrapper(bo).select(ThotThought.class,
            f -> !f.getColumn().equals("content"));
        Page<ThotThoughtVo> result = thotThoughtMapper.selectPage(pageQuery.build(), lqw, ThotThoughtVo.class);
        return TableDataInfo.build(result);
    }

    /**
     * 查询思绪信息列表
     */
    @Override
    public List<ThotThoughtVo> queryList(ThotThoughtBo bo) {
        LambdaQueryWrapper<ThotThought> lqw = buildQueryWrapper(bo).select(ThotThought.class,
            f -> !f.getColumn().equals("content"));
        return thotThoughtMapper.selectList(lqw, ThotThoughtVo.class);
    }

    @Override
    public LambdaQueryWrapper<ThotThought> buildQueryWrapper(ThotThoughtBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ThotThought> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, ThotThought::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), ThotThought::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getCode()), ThotThought::getCode, bo.getCode());
        lqw.eq(StringUtils.isNotBlank(bo.getTitle()), ThotThought::getTitle, bo.getTitle());
        lqw.eq(bo.getStatus() != null, ThotThought::getStatus, bo.getStatus());
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginPublishTime"), params.get("endPublishTime")),
            ThotThought::getPublishTime, params.get("beginPublishTime"), params.get("endPublishTime"));
        return lqw;
    }

    /**
     * 修改思绪状态
     */
    public int updateStatus(Collection<Long> ids, DataStatus dataStatus) {
        LambdaUpdateWrapper<ThotThought> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(dataStatus == DataStatus.PUBLISH, ThotThought::getPublishTime, new Date())
            .set(ThotThought::getStatus, dataStatus.status).in(ThotThought::getThoughtId, ids);
        return thotThoughtMapper.update(null, wrapper);
    }

}
