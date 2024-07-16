package org.dromara.platform.controller.app;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.app.bo.AppSubscribeBo;
import org.dromara.basis.app.service.AppSubscribeService;
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
import org.dromara.platform.vo.app.AppSubscribeVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * App订阅
 *
 * @author chanbeiyu
 * @date 2024-07-14
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/subscribe")
public class AppSubscribeController extends BaseController {

    private final AppSubscribeService appSubscribeService;

    /**
     * 查询App订阅列表
     */
    @SaCheckPermission("app:subscribe:list")
    @GetMapping("/list")
    public TableDataInfo<AppSubscribeVo> list(AppSubscribeBo bo, PageQuery pageQuery) {
        return appSubscribeService.selectTableList(bo, pageQuery, AppSubscribeVo.class);
    }

    /**
     * 导出App订阅列表
     */
    @SaCheckPermission("app:subscribe:export")
    @Log(title = "App订阅", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AppSubscribeBo bo, HttpServletResponse response) {
        List<AppSubscribeVo> list = appSubscribeService.selectList(bo, AppSubscribeVo.class);
        ExcelUtil.exportExcel(list, "App订阅", AppSubscribeVo.class, response);
    }

    /**
     * 获取App订阅详细信息
     *
     * @param subscribeId 主键
     */
    @SaCheckPermission("app:subscribe:query")
    @GetMapping("/{subscribeId}")
    public R<AppSubscribeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long subscribeId) {
        return R.ok(appSubscribeService.selectById(subscribeId, AppSubscribeVo.class));
    }

    /**
     * 新增App订阅
     */
    @SaCheckPermission("app:subscribe:add")
    @Log(title = "App订阅", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AppSubscribeBo bo) {
        return toAjax(appSubscribeService.insert(bo));
    }

    /**
     * 修改App订阅
     */
    @SaCheckPermission("app:subscribe:edit")
    @Log(title = "App订阅", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AppSubscribeBo bo) {
        return toAjax(appSubscribeService.update(bo));
    }

    /**
     * 删除App订阅
     *
     * @param subscribeIds 主键串
     */
    @SaCheckPermission("app:subscribe:remove")
    @Log(title = "App订阅", businessType = BusinessType.DELETE)
    @DeleteMapping("/{subscribeIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] subscribeIds) {
        return toAjax(appSubscribeService.deleteByIds(List.of(subscribeIds)));
    }
}
