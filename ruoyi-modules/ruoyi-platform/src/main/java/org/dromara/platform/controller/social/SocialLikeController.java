package org.dromara.platform.controller.social;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.social.bo.SocialLikeBo;
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
import org.dromara.platform.vo.social.SocialLikeVo;
import org.dromara.basis.social.service.SocialLikeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 点赞信息
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/social/like")
public class SocialLikeController extends BaseController {

    private final SocialLikeService socialLikeService;

    /**
     * 查询点赞信息列表
     */
    @SaCheckPermission("social:like:list")
    @GetMapping("/list")
    public TableDataInfo<SocialLikeVo> list(SocialLikeBo bo, PageQuery pageQuery) {
        return socialLikeService.queryPageList(bo, pageQuery, SocialLikeVo.class);
    }

    /**
     * 导出点赞信息列表
     */
    @SaCheckPermission("social:like:export")
    @Log(title = "点赞信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SocialLikeBo bo, HttpServletResponse response) {
        List<SocialLikeVo> list = socialLikeService.queryList(bo, SocialLikeVo.class);
        ExcelUtil.exportExcel(list, "点赞信息", SocialLikeVo.class, response);
    }

    /**
     * 获取点赞信息详细信息
     *
     * @param likeId 主键
     */
    @SaCheckPermission("social:like:query")
    @GetMapping("/{likeId}")
    public R<SocialLikeVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long likeId) {
        return R.ok(socialLikeService.queryById(likeId, SocialLikeVo.class));
    }

    /**
     * 新增点赞信息
     */
    @SaCheckPermission("social:like:add")
    @Log(title = "点赞信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SocialLikeBo bo) {
        return toAjax(socialLikeService.insertByBo(bo));
    }

    /**
     * 修改点赞信息
     */
    @SaCheckPermission("social:like:edit")
    @Log(title = "点赞信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SocialLikeBo bo) {
        return toAjax(socialLikeService.updateByBo(bo));
    }

    /**
     * 删除点赞信息
     *
     * @param likeIds 主键串
     */
    @SaCheckPermission("social:like:remove")
    @Log(title = "点赞信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{likeIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] likeIds) {
        return toAjax(socialLikeService.deleteByIds(List.of(likeIds)));
    }
}
