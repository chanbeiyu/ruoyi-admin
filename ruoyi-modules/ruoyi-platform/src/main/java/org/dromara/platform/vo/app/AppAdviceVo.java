package org.dromara.platform.vo.app;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.app.entity.AppAdvice;
import org.dromara.basis.constant.TranslationConst;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.core.AppBaseVo;

import java.util.Date;


/**
 * 意见反馈信息视图对象 app_advice
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = AppAdvice.class)
public class AppAdviceVo extends AppBaseVo {

    /**
     * 留言id
     */
    @ExcelProperty(value = "留言id")
    private Long adviceId;

    /**
     * 成员id
     */
    @ExcelProperty(value = "成员id")
    private Long memberId;

    /**
     * 成员名称
     */
    @ExcelProperty(value = "成员名称")
    @Translation(type = TranslationConst.TYPE_KEY, mapper = "memberId", other = TranslationConst.MEMBER_INFO_NAME)
    private String memberName;

    /**
     * 联系方式
     */
    @ExcelProperty(value = "联系方式")
    private String contact;

    /**
     * 反馈内容
     */
    @ExcelProperty(value = "反馈内容")
    private String content;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

}
