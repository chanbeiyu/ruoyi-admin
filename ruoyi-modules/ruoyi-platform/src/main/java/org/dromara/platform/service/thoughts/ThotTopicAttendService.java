package org.dromara.platform.service.thoughts;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.platform.domain.thoughts.ThotTopicAttend;
import org.dromara.platform.domain.thoughts.vo.ThotTopicAttendVo;
import org.dromara.platform.mapper.ThotTopicAttendMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 话题参与Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-06
 */
@RequiredArgsConstructor
@Service
public class ThotTopicAttendService implements IBaseService<ThotTopicAttend, ThotTopicAttendVo, ThotTopicAttend> {

    private final ThotTopicAttendMapper thotTopicAttendMapper;

    @Override
    public IBaseMapper<ThotTopicAttend> mapper() {
        return thotTopicAttendMapper;
    }

    public List<ThotTopicAttend> queryByTopicId(Long topicId) {
        ThotTopicAttend thotTopicAttend = new ThotTopicAttend();
        thotTopicAttend.setTopicId(topicId);
        LambdaQueryWrapper<ThotTopicAttend> lqw = buildQueryWrapper(thotTopicAttend);
        return thotTopicAttendMapper.selectList(lqw);
    }

    public List<ThotTopicAttend> queryByAlbumId(Long albumId) {
        ThotTopicAttend thotTopicAttend = new ThotTopicAttend();
        thotTopicAttend.setAlbumId(albumId);
        LambdaQueryWrapper<ThotTopicAttend> lqw = buildQueryWrapper(thotTopicAttend);
        return thotTopicAttendMapper.selectList(lqw);
    }

    public List<ThotTopicAttend> queryByThoughtId(Long thoughtId) {
        ThotTopicAttend thotTopicAttend = new ThotTopicAttend();
        thotTopicAttend.setThoughtId(thoughtId);
        LambdaQueryWrapper<ThotTopicAttend> lqw = buildQueryWrapper(thotTopicAttend);
        return thotTopicAttendMapper.selectList(lqw);
    }

    public List<ThotTopicAttend> queryByAttendId(Long attendId) {
        ThotTopicAttend thotTopicAttend = new ThotTopicAttend();
        thotTopicAttend.setAttendId(attendId);
        LambdaQueryWrapper<ThotTopicAttend> lqw = buildQueryWrapper(thotTopicAttend);
        return thotTopicAttendMapper.selectList(lqw);
    }

    @Override
    public LambdaQueryWrapper<ThotTopicAttend> buildQueryWrapper(ThotTopicAttend thotTopicAttend) {
        LambdaQueryWrapper<ThotTopicAttend> lqw = Wrappers.lambdaQuery();
        lqw.eq(thotTopicAttend.getAttendId() != null, ThotTopicAttend::getAttendId, thotTopicAttend.getAttendId());
        lqw.eq(thotTopicAttend.getTopicId() != null, ThotTopicAttend::getTopicId, thotTopicAttend.getTopicId());
        lqw.eq(thotTopicAttend.getAlbumId() != null, ThotTopicAttend::getAlbumId, thotTopicAttend.getAlbumId());
        lqw.eq(thotTopicAttend.getThoughtId() != null, ThotTopicAttend::getThoughtId, thotTopicAttend.getThoughtId());
        return lqw;
    }

}
