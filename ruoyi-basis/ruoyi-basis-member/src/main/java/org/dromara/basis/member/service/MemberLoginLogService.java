package org.dromara.basis.member.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.basis.member.bo.MemberLoginLogBo;
import org.dromara.basis.member.entity.MemberLoginLog;
import org.dromara.basis.member.mapper.MemberLoginLogMapper;
import org.dromara.common.core.constant.Constants;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.ServletUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.utils.ip.AddressUtils;
import org.dromara.common.log.event.LogininforEvent;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.common.satoken.utils.LoginHelper;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 系统访问日志情况信息 服务层处理
 *
 * @author chanbeiyu
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class MemberLoginLogService implements IBaseService<MemberLoginLog, MemberLoginLogBo> {

    private final MemberLoginLogMapper memberLoginLogMapper;

    //private final ISysClientService clientService;


    @Override
    public IBaseMapper<MemberLoginLog> mapper() {
        return memberLoginLogMapper;
    }

    @Override
    public LambdaQueryWrapper<MemberLoginLog> buildQueryWrapper(MemberLoginLogBo memberLoginLogBo) {
        Map<String, Object> params = memberLoginLogBo.getParams();
        return new LambdaQueryWrapper<MemberLoginLog>()
            .like(StringUtils.isNotBlank(memberLoginLogBo.getIpaddr()), MemberLoginLog::getIpaddr, memberLoginLogBo.getIpaddr())
            .eq(StringUtils.isNotBlank(memberLoginLogBo.getStatus()), MemberLoginLog::getStatus, memberLoginLogBo.getStatus())
            .like(StringUtils.isNotBlank(memberLoginLogBo.getUserName()), MemberLoginLog::getUserName, memberLoginLogBo.getUserName())
            .between(params.get("beginTime") != null && params.get("endTime") != null,
                MemberLoginLog::getLoginTime, params.get("beginTime"), params.get("endTime"))
            .orderByDesc(MemberLoginLog::getInfoId);
    }

    /**
     * 记录登录信息
     *
     * @param logininforEvent 登录事件
     */
    @Async
    @EventListener
    public void recordLogininfor(LogininforEvent logininforEvent) {
        HttpServletRequest request = logininforEvent.getRequest();
        final UserAgent userAgent = UserAgentUtil.parse(request.getHeader("User-Agent"));
        final String ip = ServletUtils.getClientIP(request);
        // 客户端信息
        String clientid = request.getHeader(LoginHelper.CLIENT_KEY);
//        SysClient client = null;
//        if (StringUtils.isNotBlank(clientid)) {
//            client = clientService.queryByClientId(clientid);
//        }

        String address = AddressUtils.getRealAddressByIP(ip);
        StringBuilder s = new StringBuilder();
        s.append(getBlock(ip));
        s.append(address);
        s.append(getBlock(logininforEvent.getUsername()));
        s.append(getBlock(logininforEvent.getStatus()));
        s.append(getBlock(logininforEvent.getMessage()));
        // 打印信息到日志
        log.info(s.toString(), logininforEvent.getArgs());
        // 获取客户端操作系统
        String os = userAgent.getOs().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();
        // 封装对象
        MemberLoginLogBo logininfor = new MemberLoginLogBo();
        logininfor.setTenantId(logininforEvent.getTenantId());
        logininfor.setUserName(logininforEvent.getUsername());
//        if (ObjectUtil.isNotNull(client)) {
//            logininfor.setClientKey(client.getClientKey());
//            logininfor.setDeviceType(client.getDeviceType());
//        }
        logininfor.setIpaddr(ip);
        logininfor.setLoginLocation(address);
        logininfor.setBrowser(browser);
        logininfor.setOs(os);
        logininfor.setMsg(logininforEvent.getMessage());
        // 日志状态
        if (StringUtils.equalsAny(logininforEvent.getStatus(), Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
            logininfor.setStatus(Constants.SUCCESS);
        } else if (Constants.LOGIN_FAIL.equals(logininforEvent.getStatus())) {
            logininfor.setStatus(Constants.FAIL);
        }
        // 插入数据
        insertLogininfor(logininfor);
    }

    private String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }

    /**
     * 新增系统登录日志
     *
     * @param bo 访问日志对象
     */
    public void insertLogininfor(MemberLoginLogBo bo) {
        MemberLoginLog logininfor = MapstructUtils.convert(bo, MemberLoginLog.class);
        logininfor.setLoginTime(new Date());
        memberLoginLogMapper.insert(logininfor);
    }

    /**
     * 清空系统登录日志
     */
    public void cleanLogininfor() {
        memberLoginLogMapper.delete(new LambdaQueryWrapper<>());
    }
}
