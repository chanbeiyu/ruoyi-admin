package org.dromara.platform.service.thoughts;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;

import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.platform.domain.ThotAlbumThought;
import org.dromara.platform.domain.vo.ThotAlbumThoughtVo;
import org.dromara.platform.mapper.ThotAlbumThoughtMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 思集信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-06
 */
@RequiredArgsConstructor
@Service
public class ThotAlbumThoughtService implements IBaseService<ThotAlbumThought, ThotAlbumThoughtVo, ThotAlbumThought> {

    private final ThotAlbumThoughtMapper thotAlbumThoughtMapper;

    @Override
    public IBaseMapper<ThotAlbumThought> mapper() {
        return thotAlbumThoughtMapper;
    }

    public ThotAlbumThoughtVo queryById(Long albumId, Long thoughtId) {
        return thotAlbumThoughtMapper.selectAlbumThought(albumId, thoughtId);
    }

    public List<ThotAlbumThoughtVo> queryByAlbumId(Long albumId) {
        return thotAlbumThoughtMapper.selectAlbumThoughtByAlbumId(albumId);
    }

    public List<ThotAlbumThoughtVo> queryByThoughtId(Long thoughtId) {
        return thotAlbumThoughtMapper.selectAlbumThoughtByThoughtId(thoughtId);
    }

    @Override
    public LambdaQueryWrapper<ThotAlbumThought> buildQueryWrapper(ThotAlbumThought thotAlbumThought) {
        LambdaQueryWrapper<ThotAlbumThought> lqw = Wrappers.lambdaQuery();
        lqw.eq(thotAlbumThought.getAlbumId() != null, ThotAlbumThought::getAlbumId, thotAlbumThought.getAlbumId());
        lqw.eq(thotAlbumThought.getThoughtId() != null, ThotAlbumThought::getThoughtId, thotAlbumThought.getThoughtId());
        lqw.eq(thotAlbumThought.getIsCover() != null, ThotAlbumThought::getIsCover, thotAlbumThought.getIsCover());
        return lqw;
    }

    public Boolean insertBatch(List<ThotAlbumThought> adds) {
        return thotAlbumThoughtMapper.insertBatch(adds);
    }

    public Boolean updateIsCover(Long id, String isCover) {
        ThotAlbumThought update = new ThotAlbumThought();
        update.setId(id);
        update.setIsCover(isCover);
        return thotAlbumThoughtMapper.updateById(update) > 0;
    }

    public void deleteByIds(Long albumId) {
        QueryWrapper<ThotAlbumThought> wrapper = new QueryWrapper<>();
        wrapper.eq("album_id", albumId);
        thotAlbumThoughtMapper.delete(wrapper);
    }

}
