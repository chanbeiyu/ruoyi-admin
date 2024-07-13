package org.dromara.basis.apptw.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 抽烟记录对象 apptw_smoke
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Data
@TableName("apptw_smoke")
public class ApptwSmoke {

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
     * 喜好ID
     */
    private Long likeId;

    /**
     * 品类ID
     */
    private Long tobaccoId;

    /**
     * 时间
     */
    private Date dateTime;

    /**
     * 焦油量
     */
    private Double tar;

    /**
     * 烟气烟碱量
     */
    private Double nicotine;

    /**
     * 烟气一氧化碳量
     */
    private Double co;

    /**
     * 支出
     */
    private Double spend;

    /**
     * 描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;


}
