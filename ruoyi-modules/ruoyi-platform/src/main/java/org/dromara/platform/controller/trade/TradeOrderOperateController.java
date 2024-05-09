package org.dromara.platform.controller.trade;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.bo.TradeOrderOperateBo;
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
import org.dromara.platform.domain.vo.trade.TradeOrderOperateVo;
import org.dromara.platform.service.trade.TradeOrderOperateService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单操作历史记录
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/trade/order/operate")
public class TradeOrderOperateController extends BaseController {

    private final TradeOrderOperateService tradeOrderOperateService;

    /**
     * 查询订单操作历史记录列表
     */
    @SaCheckPermission("trade:order:operate:list")
    @GetMapping("/list")
    public TableDataInfo<TradeOrderOperateVo> list(TradeOrderOperateBo bo, PageQuery pageQuery) {
        return tradeOrderOperateService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出订单操作历史记录列表
     */
    @SaCheckPermission("trade:order:operate:export")
    @Log(title = "订单操作历史记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TradeOrderOperateBo bo, HttpServletResponse response) {
        List<TradeOrderOperateVo> list = tradeOrderOperateService.queryList(bo);
        ExcelUtil.exportExcel(list, "订单操作历史记录", TradeOrderOperateVo.class, response);
    }

    /**
     * 获取订单操作历史记录详细信息
     *
     * @param operateId 主键
     */
    @SaCheckPermission("trade:order:operate:query")
    @GetMapping("/{operateId}")
    public R<TradeOrderOperateVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long operateId) {
        return R.ok(tradeOrderOperateService.queryById(operateId));
    }

    /**
     * 新增订单操作历史记录
     */
    @SaCheckPermission("trade:order:operate:add")
    @Log(title = "订单操作历史记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TradeOrderOperateBo bo) {
        return toAjax(tradeOrderOperateService.insertByBo(bo));
    }

    /**
     * 修改订单操作历史记录
     */
    @SaCheckPermission("trade:order:operate:edit")
    @Log(title = "订单操作历史记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TradeOrderOperateBo bo) {
        return toAjax(tradeOrderOperateService.updateByBo(bo));
    }

    /**
     * 删除订单操作历史记录
     *
     * @param operateIds 主键串
     */
    @SaCheckPermission("trade:order:operate:remove")
    @Log(title = "订单操作历史记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{operateIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] operateIds) {
        return toAjax(tradeOrderOperateService.deleteByIds(List.of(operateIds)));
    }
}
