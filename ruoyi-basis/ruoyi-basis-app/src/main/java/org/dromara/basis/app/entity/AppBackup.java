package org.dromara.basis.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.util.Date;

/**
 * 备份信息对象 app_backup
 *
 * @author chanbeiyu
 * @date 2024-07-16
 */
@Data
@TableName("app_backup")
public class AppBackup {

    /**
     * 备份id
     */
    @TableId(value = "backup_id")
    private Long backupId;

    /**
     * 应用ID
     */
    private Long appId;

    /**
     * 成员ID
     */
    private Long memberId;

    /**
     * 备份标题
     */
    private String title;

    /**
     * 备份内容
     */
    private String content;

    /**
     * 备份时间
     */
    private Date time;

    /**
     * 备注信息
     */
    private String description;


}
