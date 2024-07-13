package org.dromara.basis.app.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.basis.app.entity.AppInfo;
import org.dromara.basis.app.entity.AppUser;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户应用信息Mapper接口
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Repository
public interface AppUserMapper extends IBaseMapper<AppUser> {

    List<AppInfo> selectByUserId(@Param("userId") Long userId);

}
