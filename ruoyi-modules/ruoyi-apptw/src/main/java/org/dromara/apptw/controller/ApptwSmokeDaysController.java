package org.dromara.apptw.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.apptw.bo.ApptwSmokeDaysBo;
import org.dromara.basis.apptw.service.ApptwSmokeDaysService;
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
import org.dromara.platform.vo.ApptwSmokeDaysVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 日抽烟数据
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/apptw/days")
public class ApptwSmokeDaysController extends BaseController {

    private final ApptwSmokeDaysService apptwSmokeDaysService;

    /**
     * 查询日抽烟数据列表
     */
    @SaCheckPermission("apptw:days:list")
    @GetMapping("/list")
    public TableDataInfo<ApptwSmokeDaysVo> list(ApptwSmokeDaysBo bo, PageQuery pageQuery) {
        return apptwSmokeDaysService.queryPageList(bo, pageQuery, ApptwSmokeDaysVo.class);
    }

    /**
     * 导出日抽烟数据列表
     */
    @SaCheckPermission("apptw:days:export")
    @Log(title = "日抽烟数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ApptwSmokeDaysBo bo, HttpServletResponse response) {
        List<ApptwSmokeDaysVo> list = apptwSmokeDaysService.queryList(bo, ApptwSmokeDaysVo.class);
        ExcelUtil.exportExcel(list, "日抽烟数据", ApptwSmokeDaysVo.class, response);
    }

    /**
     * 获取日抽烟数据详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("apptw:days:query")
    @GetMapping("/{id}")
    public R<ApptwSmokeDaysVo> getInfo(@NotNull(message = "主键不能为空")
                                       @PathVariable Long id) {
        return R.ok(apptwSmokeDaysService.queryById(id, ApptwSmokeDaysVo.class));
    }

    /**
     * 新增日抽烟数据
     */
    @SaCheckPermission("apptw:days:add")
    @Log(title = "日抽烟数据", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ApptwSmokeDaysBo bo) {
        return toAjax(apptwSmokeDaysService.insertByBo(bo));
    }

    /**
     * 修改日抽烟数据
     */
    @SaCheckPermission("apptw:days:edit")
    @Log(title = "日抽烟数据", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ApptwSmokeDaysBo bo) {
        return toAjax(apptwSmokeDaysService.updateByBo(bo));
    }

    /**
     * 删除日抽烟数据
     *
     * @param ids 主键串
     */
    @SaCheckPermission("apptw:days:remove")
    @Log(title = "日抽烟数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(apptwSmokeDaysService.deleteByIds(List.of(ids)));
    }
}
