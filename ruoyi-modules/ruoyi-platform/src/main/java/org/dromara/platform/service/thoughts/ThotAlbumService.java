package org.dromara.platform.service.thoughts;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.platform.constant.DataStatus;
import org.dromara.platform.domain.ThotAlbum;
import org.dromara.platform.domain.ThotAlbumThought;
import org.dromara.platform.domain.bo.ThotAlbumBo;
import org.dromara.platform.domain.bo.ThotAlbumThoughtBo;
import org.dromara.platform.domain.vo.ThotAlbumThoughtVo;
import org.dromara.platform.domain.vo.thoughts.ThotAlbumVo;
import org.dromara.platform.domain.vo.thoughts.ThotThoughtVo;
import org.dromara.platform.mapper.ThotAlbumMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 思集信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class ThotAlbumService implements IBaseService<ThotAlbum, ThotAlbumVo, ThotAlbumBo> {

    private final ThotAlbumMapper thotAlbumMapper;

    private final ThotThoughtService thotThoughtService;
    private final ThotAlbumThoughtService thotAlbumThoughtService;

    @Override
    public IBaseMapper<ThotAlbum> mapper() {
        return thotAlbumMapper;
    }

    /**
     * 查询思集信息
     */
    @Override
    public ThotAlbumVo queryById(Long albumId) {
        ThotAlbumVo thotAlbumVo = thotAlbumMapper.selectById(albumId, ThotAlbumVo.class);
        List<ThotAlbumThoughtVo> thotAlbumThoughts = thotAlbumThoughtService.queryByAlbumId(albumId);
        thotAlbumVo.setAlbumThoughts(thotAlbumThoughts);
        return thotAlbumVo;
    }

    /**
     * 查询思集信息
     */
    public List<ThotAlbumThoughtVo> queryAlbumThoughtByIds(List<Long> thoughtIds) {
        List<ThotThoughtVo> thotAlbumVo = thotThoughtService.queryByIds(thoughtIds);
        return thotAlbumVo.stream().map(o -> {
            ThotAlbumThoughtVo thotAlbumThoughtVo = new ThotAlbumThoughtVo();
            thotAlbumThoughtVo.setThoughtId(o.getThoughtId());
            thotAlbumThoughtVo.setCode(o.getCode());
            thotAlbumThoughtVo.setTitle(o.getTitle());
            thotAlbumThoughtVo.setMainImg(o.getMainImg());
            thotAlbumThoughtVo.setStatus(o.getStatus());
            thotAlbumThoughtVo.setIsCover("N");
            return thotAlbumThoughtVo;
        }).toList();
    }

    /**
     * 查询思集信息列表
     */
    @Override
    public TableDataInfo<ThotAlbumVo> queryPageList(ThotAlbumBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ThotAlbum> lqw = buildQueryWrapper(bo);
        Page<ThotAlbumVo> result = thotAlbumMapper.selectPage(pageQuery.build(), lqw, thotAlbum -> {
            ThotAlbumVo albumVo = MapstructUtils.convert(thotAlbum, ThotAlbumVo.class);
            assert albumVo != null;
            List<ThotAlbumThoughtVo> thotAlbumThoughts = thotAlbumThoughtService.queryByAlbumId(thotAlbum.getAlbumId());
            albumVo.setAlbumThoughts(thotAlbumThoughts);
            return albumVo;
        });
        return TableDataInfo.build(result);
    }

    @Override
    public LambdaQueryWrapper<ThotAlbum> buildQueryWrapper(ThotAlbumBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ThotAlbum> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, ThotAlbum::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), ThotAlbum::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getAlbumTitle()), ThotAlbum::getAlbumTitle, bo.getAlbumTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), ThotAlbum::getDescription, bo.getDescription());
        lqw.eq(bo.getStatus() != null, ThotAlbum::getStatus, bo.getStatus());
        lqw.between(params.get("beginPublishTime") != null && params.get("endPublishTime") != null,
            ThotAlbum::getPublishTime, params.get("beginPublishTime"), params.get("endPublishTime"));
        return lqw;
    }

    /**
     * 新增思集信息
     */
    @Override
    public Boolean insertByBo(ThotAlbumBo bo) {
        ThotAlbum add = MapstructUtils.convert(bo, ThotAlbum.class);
        boolean flag = thotAlbumMapper.insert(add) > 0;
        assert add != null;
        bo.setAlbumId(add.getAlbumId());

        List<ThotAlbumThought> albumThoughtList = MapstructUtils.convert(bo.getAlbumThoughts(), ThotAlbumThought.class);
        flag = flag && thotAlbumThoughtService.insertBatch(albumThoughtList);

        List<ThotAlbumThoughtBo> albumThoughts = bo.getAlbumThoughts();
        List<ThotAlbumThought> thotAlbumThoughts = MapstructUtils.convert(albumThoughts, ThotAlbumThought.class);
        thotAlbumThoughtService.deleteByIds(bo.getAlbumId());
        thotAlbumThoughts.forEach(o -> {
            o.setAlbumId(bo.getAlbumId());
            o.setIsCover(o.getIsCover());
        });
        thotAlbumThoughtService.insertBatch(thotAlbumThoughts);
        return flag;
    }

    /**
     * 修改思集信息
     */
    @Override
    public Boolean updateByBo(ThotAlbumBo bo) {
        ThotAlbum update = MapstructUtils.convert(bo, ThotAlbum.class);
        List<ThotAlbumThoughtBo> albumThoughts = bo.getAlbumThoughts();
        List<ThotAlbumThought> thotAlbumThoughts = MapstructUtils.convert(albumThoughts, ThotAlbumThought.class);
        thotAlbumThoughtService.deleteByIds(bo.getAlbumId());
        thotAlbumThoughts.forEach(o -> {
            o.setAlbumId(bo.getAlbumId());
            o.setIsCover(o.getIsCover());
        });
        thotAlbumThoughtService.insertBatch(thotAlbumThoughts);
        return thotAlbumMapper.updateById(update) > 0;
    }

    /**
     * 修改思集状态
     */
    public int updateStatus(Collection<Long> ids, DataStatus dataStatus) {
        LambdaUpdateWrapper<ThotAlbum> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(dataStatus == DataStatus.PUBLISH, ThotAlbum::getPublishTime, new Date())
            .set(ThotAlbum::getStatus, dataStatus.status)
            .in(ThotAlbum::getAlbumId, ids);
        return thotAlbumMapper.update(null, wrapper);
    }

    /**
     * 修改封面状态
     */
    public Boolean updateCoverStatus(Long id, String cover) {
        return thotAlbumThoughtService.updateIsCover(id, cover);
    }

}
