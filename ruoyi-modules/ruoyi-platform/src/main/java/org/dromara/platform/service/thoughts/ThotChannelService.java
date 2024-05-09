package org.dromara.platform.service.thoughts;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.platform.domain.ThotChannel;
import org.dromara.platform.domain.bo.ThotChannelBo;
import org.dromara.platform.domain.vo.thoughts.ThotChannelVo;
import org.dromara.platform.mapper.ThotChannelMapper;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * 频道信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class ThotChannelService implements IBaseService<ThotChannel, ThotChannelVo, ThotChannelBo> {

    private final ThotChannelMapper baseMapper;

    @Override
    public IBaseMapper<ThotChannel> mapper() {
        return baseMapper;
    }

    @Override
    public LambdaQueryWrapper<ThotChannel> buildQueryWrapper(ThotChannelBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ThotChannel> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), ThotChannel::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), ThotChannel::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getChannelCode()), ThotChannel::getChannelCode, bo.getChannelCode());
        lqw.like(StringUtils.isNotBlank(bo.getChannelName()), ThotChannel::getChannelName, bo.getChannelName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ThotChannel::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 修改频道状态
     *
     * @param channelId 频道ID
     * @param status    频道状态
     * @return 结果
     */
    public int updateStatus(Long channelId, String status) {
        return baseMapper.update(null, new LambdaUpdateWrapper<ThotChannel>().set(ThotChannel::getStatus, status)
            .eq(ThotChannel::getChannelId, channelId));
    }

}
