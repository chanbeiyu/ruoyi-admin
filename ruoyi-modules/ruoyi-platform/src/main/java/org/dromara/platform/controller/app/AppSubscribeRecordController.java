package org.dromara.platform.controller.app;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.dromara.basis.app.bo.AppSubscribeRecordBo;
import org.dromara.basis.app.service.AppSubscribeRecordService;
import org.dromara.platform.vo.app.AppSubscribeRecordVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * App订阅记录
 *
 * @author chanbeiyu
 * @date 2024-07-14
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/subscribe/record")
public class AppSubscribeRecordController extends BaseController {

    private final AppSubscribeRecordService appSubscribeRecordService;

    /**
     * 查询App订阅记录列表
     */
    @SaCheckPermission("app:subscribe:record:list")
    @GetMapping("/list")
    public TableDataInfo<AppSubscribeRecordVo> list(AppSubscribeRecordBo bo, PageQuery pageQuery) {
        return appSubscribeRecordService.selectTableList(bo, pageQuery, AppSubscribeRecordVo.class);
    }

    /**
     * 导出App订阅记录列表
     */
    @SaCheckPermission("app:subscribe:record:export")
    @Log(title = "App订阅记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AppSubscribeRecordBo bo, HttpServletResponse response) {
        List<AppSubscribeRecordVo> list = appSubscribeRecordService.selectList(bo, AppSubscribeRecordVo.class);
        ExcelUtil.exportExcel(list, "App订阅记录", AppSubscribeRecordVo.class, response);
    }

    /**
     * 获取App订阅记录详细信息
     *
     * @param recordId 主键
     */
    @SaCheckPermission("app:subscribe:record:query")
    @GetMapping("/{recordId}")
    public R<AppSubscribeRecordVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long recordId) {
        return R.ok(appSubscribeRecordService.selectById(recordId, AppSubscribeRecordVo.class));
    }

    /**
     * 新增App订阅记录
     */
    @SaCheckPermission("app:subscribe:record:add")
    @Log(title = "App订阅记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AppSubscribeRecordBo bo) {
        return toAjax(appSubscribeRecordService.insert(bo));
    }

    /**
     * 修改App订阅记录
     */
    @SaCheckPermission("app:subscribe:record:edit")
    @Log(title = "App订阅记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AppSubscribeRecordBo bo) {
        return toAjax(appSubscribeRecordService.update(bo));
    }

    /**
     * 删除App订阅记录
     *
     * @param recordIds 主键串
     */
    @SaCheckPermission("app:subscribe:record:remove")
    @Log(title = "App订阅记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{recordIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] recordIds) {
        return toAjax(appSubscribeRecordService.deleteByIds(List.of(recordIds)));
    }
}
