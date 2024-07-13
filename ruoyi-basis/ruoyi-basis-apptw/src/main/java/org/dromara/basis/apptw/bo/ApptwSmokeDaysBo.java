package org.dromara.basis.apptw.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.apptw.entity.ApptwSmokeDays;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseBo;

import java.time.LocalDate;
import java.util.Date;

/**
 * 日抽烟数据业务对象 apptw_smoke_days
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ApptwSmokeDays.class, reverseConvertGenerate = false)
public class ApptwSmokeDaysBo extends BaseBo {

    @NotNull(message = "ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 成员ID
     */
    @NotNull(message = "成员ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long memberId;

    /**
     * 日期
     */
    @NotNull(message = "日期不能为空", groups = {AddGroup.class, EditGroup.class})
    private LocalDate day;

    /**
     * 抽烟数
     */
    @NotNull(message = "抽烟数不能为空", groups = {AddGroup.class, EditGroup.class})
    private Integer total;

    /**
     * 焦油量
     */
    @NotNull(message = "焦油量不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long tar;

    /**
     * 烟气烟碱量
     */
    @NotNull(message = "烟气烟碱量不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long nicotine;

    /**
     * 烟气一氧化碳量
     */
    @NotNull(message = "烟气一氧化碳量不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long co;

    /**
     * 支出
     */
    @NotNull(message = "支出不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long spend;

    /**
     * 描述
     */
    private String description;


}
