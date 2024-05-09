package org.dromara.platform.controller.trade;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import org.dromara.platform.domain.bo.TradeOrderItemBo;
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
import org.dromara.platform.domain.vo.trade.TradeOrderItemVo;
import org.dromara.platform.service.trade.TradeOrderItemService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单商品
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/trade/order/item")
public class TradeOrderItemController extends BaseController {

    private final TradeOrderItemService tradeOrderItemService;

    /**
     * 查询订单商品列表
     */
    @SaCheckPermission("trade:order:item:list")
    @GetMapping("/list")
    public TableDataInfo<TradeOrderItemVo> list(TradeOrderItemBo bo, PageQuery pageQuery) {
        return tradeOrderItemService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出订单商品列表
     */
    @SaCheckPermission("trade:order:item:export")
    @Log(title = "订单商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TradeOrderItemBo bo, HttpServletResponse response) {
        List<TradeOrderItemVo> list = tradeOrderItemService.queryList(bo);
        ExcelUtil.exportExcel(list, "订单商品", TradeOrderItemVo.class, response);
    }

    /**
     * 获取订单商品详细信息
     *
     * @param itemId 主键
     */
    @SaCheckPermission("trade:order:item:query")
    @GetMapping("/{itemId}")
    public R<TradeOrderItemVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long itemId) {
        return R.ok(tradeOrderItemService.queryById(itemId));
    }

    /**
     * 新增订单商品
     */
    @SaCheckPermission("trade:order:item:add")
    @Log(title = "订单商品", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TradeOrderItemBo bo) {
        return toAjax(tradeOrderItemService.insertByBo(bo));
    }

    /**
     * 修改订单商品
     */
    @SaCheckPermission("trade:order:item:edit")
    @Log(title = "订单商品", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TradeOrderItemBo bo) {
        return toAjax(tradeOrderItemService.updateByBo(bo));
    }

    /**
     * 删除订单商品
     *
     * @param itemIds 主键串
     */
    @SaCheckPermission("trade:order:item:remove")
    @Log(title = "订单商品", businessType = BusinessType.DELETE)
    @DeleteMapping("/{itemIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] itemIds) {
        return toAjax(tradeOrderItemService.deleteByIds(List.of(itemIds)));
    }
}
