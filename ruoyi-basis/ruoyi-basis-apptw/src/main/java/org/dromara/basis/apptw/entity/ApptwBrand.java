package org.dromara.basis.apptw.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 香烟品牌对象 apptw_brand
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Data
@TableName("apptw_brand")
public class ApptwBrand {

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌英文名称
     */
    private String enname;

    /**
     * 类型1大陆2港澳台3国外品牌4历史品牌
     */
    private String type;

    /**
     * 简介
     */
    private String description;

    /**
     * 备注
     */
    private String remark;


}
