package org.dromara.platform.controller.app;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.app.bo.AppAdviceBo;
import org.dromara.basis.app.service.AppAdviceService;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.platform.vo.app.AppAdviceVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 意见反馈信息
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/advice")
public class AppAdviceController extends BaseController {

    private final AppAdviceService appAdviceService;

    /**
     * 查询意见反馈信息列表
     */
    @SaCheckPermission("app:advice:list")
    @GetMapping("/list")
    public TableDataInfo<AppAdviceVo> list(AppAdviceBo bo, PageQuery pageQuery) {
        return appAdviceService.selectTableList(bo, pageQuery, AppAdviceVo.class);
    }

    /**
     * 导出意见反馈信息列表
     */
    @SaCheckPermission("app:advice:export")
    @Log(title = "意见反馈信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AppAdviceBo bo, HttpServletResponse response) {
        List<AppAdviceVo> list = appAdviceService.selectList(bo, AppAdviceVo.class);
        ExcelUtil.exportExcel(list, "意见反馈信息", AppAdviceVo.class, response);
    }

    /**
     * 获取意见反馈信息详细信息
     *
     * @param adviceId 主键
     */
    @SaCheckPermission("app:advice:query")
    @GetMapping("/{adviceId}")
    public R<AppAdviceVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long adviceId) {
        return R.ok(appAdviceService.selectById(adviceId, AppAdviceVo.class));
    }

    /**
     * 新增意见反馈信息
     */
    @SaCheckPermission("app:advice:add")
    @Log(title = "意见反馈信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AppAdviceBo bo) {
        return toAjax(appAdviceService.insert(bo));
    }

    /**
     * 修改意见反馈信息
     */
    @SaCheckPermission("app:advice:edit")
    @Log(title = "意见反馈信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AppAdviceBo bo) {
        return toAjax(appAdviceService.update(bo));
    }

    /**
     * 删除意见反馈信息
     *
     * @param adviceIds 主键串
     */
    @SaCheckPermission("app:advice:remove")
    @Log(title = "意见反馈信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{adviceIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] adviceIds) {
        return toAjax(appAdviceService.deleteByIds(adviceIds));
    }
}
