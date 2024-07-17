package org.dromara.basis.app.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.app.bo.AppBackupBo;
import org.dromara.basis.app.entity.AppBackup;
import org.dromara.basis.app.mapper.AppBackupMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 备份信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2024-07-16
 */
@RequiredArgsConstructor
@Service
public class AppBackupService implements IBaseService<AppBackup, AppBackupBo> {

    private final AppBackupMapper appBackupMapper;

    @Override
    public IBaseMapper<AppBackup> mapper() {
        return appBackupMapper;
    }

    @Override
    public LambdaQueryWrapper<AppBackup> buildQueryWrapper(AppBackupBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AppBackup> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getMemberId() != null, AppBackup::getMemberId, bo.getMemberId());
        lqw.like(StringUtils.isNotBlank(bo.getTitle()), AppBackup::getTitle, bo.getTitle());
        lqw.between(params.get("beginTime") != null && params.get("endTime") != null,
            AppBackup::getTime, params.get("beginTime"), params.get("endTime"));
        return lqw;
    }

}
