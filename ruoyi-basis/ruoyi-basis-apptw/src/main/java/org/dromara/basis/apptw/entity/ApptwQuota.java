package org.dromara.basis.apptw.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 目标限额对象 apptw_quota
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Data
@TableName("apptw_quota")
public class ApptwQuota {

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 成员ID
     */
    private Long memberId;

    /**
     * 限额月份
     */
    private String month;

    /**
     * 限额月总数
     */
    private Long total;

    /**
     * 限额月平均
     */
    private Long avg;

    /**
     * 限额天数
     */
    private Long days;

    /**
     * 描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;


}
