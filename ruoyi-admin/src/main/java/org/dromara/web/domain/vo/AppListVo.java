package org.dromara.web.domain.vo;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.platform.domain.app.vo.AppInfoVo;
import org.dromara.system.domain.vo.SysTenantVo;

/**
 * 应用列表
 *
 * @author chanbeiyu
 */
@Data
@AutoMapper(target = AppInfoVo.class)
public class AppListVo {

    private Long appId;

    private String appCode;

    private String appName;

}
