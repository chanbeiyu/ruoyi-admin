package org.dromara.basis.member.service;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.member.bo.MemberOperLogBo;
import org.dromara.basis.member.entity.MemberOperLog;
import org.dromara.basis.member.mapper.MemberOperLogMapper;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.utils.ip.AddressUtils;
import org.dromara.common.log.event.OperLogEvent;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 操作日志 服务层处理
 *
 * @author chanbeiyu
 */
@RequiredArgsConstructor
@Service
public class MemberOperLogService implements IBaseService<MemberOperLog, MemberOperLogBo> {

    private final MemberOperLogMapper memberOperLogMapper;

    @Override
    public IBaseMapper<MemberOperLog> mapper() {
        return memberOperLogMapper;
    }

    @Override
    public LambdaQueryWrapper<MemberOperLog> buildQueryWrapper(MemberOperLogBo memberOperLogBo) {
        Map<String, Object> params = memberOperLogBo.getParams();
        return new LambdaQueryWrapper<MemberOperLog>()
            .like(StringUtils.isNotBlank(memberOperLogBo.getOperIp()), MemberOperLog::getOperIp, memberOperLogBo.getOperIp())
            .like(StringUtils.isNotBlank(memberOperLogBo.getTitle()), MemberOperLog::getTitle, memberOperLogBo.getTitle())
            .eq(memberOperLogBo.getBusinessType() != null && memberOperLogBo.getBusinessType() > 0,
                MemberOperLog::getBusinessType, memberOperLogBo.getBusinessType())
            .func(f -> {
                if (ArrayUtil.isNotEmpty(memberOperLogBo.getBusinessTypes())) {
                    f.in(MemberOperLog::getBusinessType, Arrays.asList(memberOperLogBo.getBusinessTypes()));
                }
            })
            .eq(memberOperLogBo.getStatus() != null && memberOperLogBo.getStatus() > 0,
                MemberOperLog::getStatus, memberOperLogBo.getStatus())
            .like(StringUtils.isNotBlank(memberOperLogBo.getOperName()), MemberOperLog::getOperName, memberOperLogBo.getOperName())
            .between(params.get("beginTime") != null && params.get("endTime") != null,
                MemberOperLog::getOperTime, params.get("beginTime"), params.get("endTime"))
            .orderByDesc(MemberOperLog::getOperId);
    }

    /**
     * 操作日志记录
     *
     * @param operLogEvent 操作日志事件
     */
    @Async
    @EventListener
    public void recordOper(OperLogEvent operLogEvent) {
        MemberOperLogBo operLog = MapstructUtils.convert(operLogEvent, MemberOperLogBo.class);
        // 远程查询操作地点
        operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
        insertOperlog(operLog);
    }

    /**
     * 新增操作日志
     *
     * @param bo 操作日志对象
     */
    public void insertOperlog(MemberOperLogBo bo) {
        MemberOperLog operLog = MapstructUtils.convert(bo, MemberOperLog.class);
        operLog.setOperTime(new Date());
        memberOperLogMapper.insert(operLog);
    }

    /**
     * 清空操作日志
     */
    public void cleanOperLog() {
        memberOperLogMapper.delete(new LambdaQueryWrapper<>());
    }

}
