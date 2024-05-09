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
import org.dromara.platform.domain.ThotCampaign;
import org.dromara.platform.domain.bo.ThotCampaignBo;
import org.dromara.platform.domain.vo.thoughts.ThotCampaignVo;
import org.dromara.platform.mapper.ThotCampaignMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 活动信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class ThotCampaignService implements IBaseService<ThotCampaign, ThotCampaignVo, ThotCampaignBo> {

    private final ThotCampaignMapper thotCampaignMapper;

    @Override
    public IBaseMapper<ThotCampaign> mapper() {
        return thotCampaignMapper;
    }

    /**
     * 查询活动信息列表
     */
    @Override
    public TableDataInfo<ThotCampaignVo> queryPageList(ThotCampaignBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ThotCampaign> lqw = buildQueryWrapper(bo).select(ThotCampaign.class,
            f -> !f.getColumn().equals("campaign_content"));
        Page<ThotCampaignVo> result = thotCampaignMapper.selectPage(pageQuery.build(), lqw, ThotCampaignVo.class);
        return TableDataInfo.build(result);
    }

    /**
     * 查询活动信息列表
     */
    @Override
    public List<ThotCampaignVo> queryList(ThotCampaignBo bo) {
        LambdaQueryWrapper<ThotCampaign> lqw = buildQueryWrapper(bo).select(ThotCampaign.class,
            f -> !f.getColumn().equals("campaign_content"));
        return thotCampaignMapper.selectList(lqw, ThotCampaignVo.class);
    }

    @Override
    public LambdaQueryWrapper<ThotCampaign> buildQueryWrapper(ThotCampaignBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ThotCampaign> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), ThotCampaign::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), ThotCampaign::getAppId, bo.getAppIds());
        lqw.eq(bo.getCampaignCode() != null, ThotCampaign::getCampaignCode, bo.getCampaignCode());
        lqw.like(StringUtils.isNotBlank(bo.getCampaignName()), ThotCampaign::getCampaignName, bo.getCampaignName());
        lqw.eq(StringUtils.isNotBlank(bo.getTypeCode()), ThotCampaign::getTypeCode, bo.getTypeCode());
        lqw.eq(bo.getStatus() != null, ThotCampaign::getStatus, bo.getStatus());
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginBeginTime"), params.get("endBeginTime")),
            ThotCampaign::getBeginTime, params.get("beginBeginTime"), params.get("endBeginTime"));
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginEndTime"), params.get("endEndTime")),
            ThotCampaign::getEndTime, params.get("beginEndTime"), params.get("endEndTime"));
        return lqw;
    }

    /**
     * 修改活动状态
     */
    public int updateStatus(Collection<Long> ids, DataStatus dataStatus) {
        return thotCampaignMapper.update(null,
            new LambdaUpdateWrapper<ThotCampaign>().set(ThotCampaign::getStatus, dataStatus.status)
                .in(ThotCampaign::getCampaignId, ids));
    }

}
