package org.dromara.platform.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户和应用关联 app_user
 *
 * @author chanbeiyu
 */
@Data
@TableName("app_user")
public class AppUser {

    /**
     * 用户ID
     */
    @TableId(type = IdType.INPUT)
    private Long userId;

    /**
     * 应用ID
     */
    private Long appId;

}
