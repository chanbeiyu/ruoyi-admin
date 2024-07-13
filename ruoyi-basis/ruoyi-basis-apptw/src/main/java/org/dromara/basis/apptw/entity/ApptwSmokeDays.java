package org.dromara.basis.apptw.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

/**
 * 日抽烟数据对象 apptw_smoke_days
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Data
@TableName("apptw_smoke_days")
public class ApptwSmokeDays {

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
     * 日期
     */
    private LocalDate day;

    /**
     * 抽烟数
     */
    private Integer total;

    /**
     * 焦油量
     */
    private Long tar;

    /**
     * 烟气烟碱量
     */
    private Long nicotine;

    /**
     * 烟气一氧化碳量
     */
    private Long co;

    /**
     * 支出
     */
    private Long spend;

    /**
     * 描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;


}
