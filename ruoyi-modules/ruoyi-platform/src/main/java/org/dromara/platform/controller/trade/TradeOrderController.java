package org.dromara.platform.controller.trade;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.trade.bo.TradeOrderBo;
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
import org.dromara.platform.vo.trade.TradeOrderVo;
import org.dromara.basis.trade.service.TradeOrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单信息
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/trade/order")
public class TradeOrderController extends BaseController {

    private final TradeOrderService tradeOrderService;

    /**
     * 查询订单信息列表
     */
    @SaCheckPermission("trade:order:list")
    @GetMapping("/list")
    public TableDataInfo<TradeOrderVo> list(TradeOrderBo bo, PageQuery pageQuery) {
        return tradeOrderService.selectTableList(bo, pageQuery, TradeOrderVo.class);
    }

    /**
     * 导出订单信息列表
     */
    @SaCheckPermission("trade:order:export")
    @Log(title = "订单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TradeOrderBo bo, HttpServletResponse response) {
        List<TradeOrderVo> list = tradeOrderService.selectList(bo, TradeOrderVo.class);
        ExcelUtil.exportExcel(list, "订单信息", TradeOrderVo.class, response);
    }

    /**
     * 获取订单信息详细信息
     *
     * @param orderId 主键
     */
    @SaCheckPermission("trade:order:query")
    @GetMapping("/{orderId}")
    public R<TradeOrderVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long orderId) {
        return R.ok(tradeOrderService.selectById(orderId, TradeOrderVo.class));
    }

    /**
     * 新增订单信息
     */
    @SaCheckPermission("trade:order:add")
    @Log(title = "订单信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TradeOrderBo bo) {
        return toAjax(tradeOrderService.insert(bo));
    }

    /**
     * 修改订单信息
     */
    @SaCheckPermission("trade:order:edit")
    @Log(title = "订单信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TradeOrderBo bo) {
        return toAjax(tradeOrderService.update(bo));
    }

    /**
     * 删除订单信息
     *
     * @param orderIds 主键串
     */
    @SaCheckPermission("trade:order:remove")
    @Log(title = "订单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orderIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] orderIds) {
        return toAjax(tradeOrderService.deleteByIds(List.of(orderIds)));
    }
}
