package org.dromara.platform.controller.social;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.bo.SocialFavoriteBo;
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
import org.dromara.platform.domain.vo.social.SocialFavoriteVo;
import org.dromara.platform.service.social.SocialFavoriteService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收藏信息
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/social/favorite")
public class SocialFavoriteController extends BaseController {

    private final SocialFavoriteService socialFavoriteService;

    /**
     * 查询收藏信息列表
     */
    @SaCheckPermission("social:favorite:list")
    @GetMapping("/list")
    public TableDataInfo<SocialFavoriteVo> list(SocialFavoriteBo bo, PageQuery pageQuery) {
        return socialFavoriteService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出收藏信息列表
     */
    @SaCheckPermission("social:favorite:export")
    @Log(title = "收藏信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SocialFavoriteBo bo, HttpServletResponse response) {
        List<SocialFavoriteVo> list = socialFavoriteService.queryList(bo);
        ExcelUtil.exportExcel(list, "收藏信息", SocialFavoriteVo.class, response);
    }

    /**
     * 获取收藏信息详细信息
     *
     * @param favoriteId 主键
     */
    @SaCheckPermission("social:favorite:query")
    @GetMapping("/{favoriteId}")
    public R<SocialFavoriteVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long favoriteId) {
        return R.ok(socialFavoriteService.queryById(favoriteId));
    }

    /**
     * 新增收藏信息
     */
    @SaCheckPermission("social:favorite:add")
    @Log(title = "收藏信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SocialFavoriteBo bo) {
        return toAjax(socialFavoriteService.insertByBo(bo));
    }

    /**
     * 修改收藏信息
     */
    @SaCheckPermission("social:favorite:edit")
    @Log(title = "收藏信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SocialFavoriteBo bo) {
        return toAjax(socialFavoriteService.updateByBo(bo));
    }

    /**
     * 删除收藏信息
     *
     * @param favoriteIds 主键串
     */
    @SaCheckPermission("social:favorite:remove")
    @Log(title = "收藏信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{favoriteIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] favoriteIds) {
        return toAjax(socialFavoriteService.deleteByIds(List.of(favoriteIds)));
    }

}
