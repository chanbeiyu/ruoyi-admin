package org.dromara.platform.mapper;

import org.dromara.platform.domain.app.AppVersion;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 应用版本信息Mapper接口
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Repository
public interface AppVersionMapper extends IBaseMapper<AppVersion> {

}
