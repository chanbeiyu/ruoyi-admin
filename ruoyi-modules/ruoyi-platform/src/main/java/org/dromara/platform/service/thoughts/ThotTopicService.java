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
import org.dromara.platform.domain.thoughts.ThotTopic;
import org.dromara.platform.domain.thoughts.bo.ThotTopicBo;
import org.dromara.platform.domain.thoughts.vo.ThotTopicVo;
import org.dromara.platform.mapper.ThotTopicMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 话题信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class ThotTopicService implements IBaseService<ThotTopic, ThotTopicVo, ThotTopicBo> {

    private final ThotTopicMapper thotTopicMapper;

    @Override
    public IBaseMapper<ThotTopic> mapper() {
        return thotTopicMapper;
    }

    /**
     * 查询话题信息列表
     */
    @Override
    public TableDataInfo<ThotTopicVo> queryPageList(ThotTopicBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ThotTopic> lqw = buildQueryWrapper(bo).select(ThotTopic.class,
            f -> !f.getColumn().equals("topic_content"));
        Page<ThotTopicVo> result = thotTopicMapper.selectPage(pageQuery.build(), lqw, ThotTopicVo.class);
        return TableDataInfo.build(result);
    }

    /**
     * 查询话题信息列表
     */
    @Override
    public List<ThotTopicVo> queryList(ThotTopicBo bo) {
        LambdaQueryWrapper<ThotTopic> lqw = buildQueryWrapper(bo).select(ThotTopic.class,
            f -> !f.getColumn().equals("topic_content"));
        return thotTopicMapper.selectList(lqw, ThotTopicVo.class);
    }

    @Override
    public LambdaQueryWrapper<ThotTopic> buildQueryWrapper(ThotTopicBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ThotTopic> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), ThotTopic::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), ThotTopic::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getTopicCode()), ThotTopic::getTopicCode, bo.getTopicCode());
        lqw.like(StringUtils.isNotBlank(bo.getTopicName()), ThotTopic::getTopicName, bo.getTopicName());
        lqw.eq(bo.getStatus() != null, ThotTopic::getStatus, bo.getStatus());
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginBeginTime"), params.get("endBeginTime")),
            ThotTopic::getBeginTime, params.get("beginBeginTime"), params.get("endBeginTime"));
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginEndTime"), params.get("endEndTime")),
            ThotTopic::getEndTime, params.get("beginEndTime"), params.get("endEndTime"));
        return lqw;
    }

    /**
     * 修改话题状态
     */
    public int updateStatus(Collection<Long> ids, DataStatus dataStatus) {
        return thotTopicMapper.update(null,
            new LambdaUpdateWrapper<ThotTopic>().set(ThotTopic::getStatus, dataStatus.status)
                .in(ThotTopic::getTopicId, ids));
    }

}
