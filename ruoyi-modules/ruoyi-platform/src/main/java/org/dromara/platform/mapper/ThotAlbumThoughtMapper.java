package org.dromara.platform.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.platform.domain.ThotAlbumThought;
import org.dromara.platform.domain.vo.ThotAlbumThoughtVo;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;

import java.util.List;

/**
 * 思集信息Mapper接口
 *
 * @author chanbeiyu
 * @date 2023-07-06
 */
public interface ThotAlbumThoughtMapper extends IBaseMapper<ThotAlbumThought> {

      List<ThotAlbumThoughtVo> selectAlbumThoughtByAlbumId(@Param("albumId") Long albumId);

      List<ThotAlbumThoughtVo> selectAlbumThoughtByThoughtId(@Param("thoughtId") Long thoughtId);

      ThotAlbumThoughtVo selectAlbumThought(@Param("albumId") Long albumId, @Param("thoughtId") Long thoughtId);

}
