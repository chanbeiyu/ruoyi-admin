package org.dromara.web.domain.vo;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.platform.vo.app.AppInfoVo;

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
