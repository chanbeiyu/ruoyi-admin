package org.dromara.platform.service.thoughts;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.platform.domain.ThotChannelThought;
import org.dromara.platform.domain.vo.thoughts.ThotChannelThoughtVo;
import org.dromara.platform.mapper.ThotChannelThoughtMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 思绪专题关联信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-06
 */
@RequiredArgsConstructor
@Service
public class ThotChannelThoughtService
    implements IBaseService<ThotChannelThought, ThotChannelThoughtVo, ThotChannelThought> {

    private final ThotChannelThoughtMapper thotChannelThoughtMapper;

    @Override
    public IBaseMapper<ThotChannelThought> mapper() {
        return thotChannelThoughtMapper;
    }

    public List<ThotChannelThought> queryByChannelId(Long channelId) {
        ThotChannelThought thotChannelThought = new ThotChannelThought();
        thotChannelThought.setChannelId(channelId);
        LambdaQueryWrapper<ThotChannelThought> lqw = buildQueryWrapper(thotChannelThought);
        return thotChannelThoughtMapper.selectList(lqw);
    }

    public List<ThotChannelThought> queryByThoughtId(Long thoughtId) {
        ThotChannelThought thotChannelThought = new ThotChannelThought();
        thotChannelThought.setThoughtId(thoughtId);
        LambdaQueryWrapper<ThotChannelThought> lqw = buildQueryWrapper(thotChannelThought);
        return thotChannelThoughtMapper.selectList(lqw);
    }

    @Override
    public LambdaQueryWrapper<ThotChannelThought> buildQueryWrapper(ThotChannelThought thotChannelThought) {
        LambdaQueryWrapper<ThotChannelThought> lqw = Wrappers.lambdaQuery();
        lqw.eq(thotChannelThought.getChannelId() != null, ThotChannelThought::getChannelId,
            thotChannelThought.getChannelId());
        lqw.eq(thotChannelThought.getThoughtId() != null, ThotChannelThought::getThoughtId,
            thotChannelThought.getThoughtId());
        return lqw;
    }

}
