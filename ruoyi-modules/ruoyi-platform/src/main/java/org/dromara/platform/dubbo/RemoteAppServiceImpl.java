//package org.dromara.platform.dubbo;
//
//import cn.hutool.core.collection.CollUtil;
//import cn.hutool.core.convert.Convert;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import lombok.RequiredArgsConstructor;
//import org.dromara.platform.domain.AppInfo;
//import org.dromara.platform.domain.AppUser;
//import org.dromara.common.core.exception.ServiceException;
//import org.dromara.common.core.utils.StreamUtils;
//import org.dromara.common.core.utils.StringUtils;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class RemoteAppServiceImpl implements RemoteAppService {
//
//    private final AppInfoMapper appInfoMapper;
//    private final AppUserMapper appUserMapper;
//
//    @Override
//    public RemoteAppInfo getAppById(Long appId) throws ServiceException {
//        return appInfoMapper.selectById(appId, RemoteAppInfo.class);
//    }
//
//    @Override
//    public List<RemoteAppInfo> getAppByIds(List<Long> appIds) throws ServiceException {
//        LambdaQueryWrapper<AppInfo> lqw = Wrappers.lambdaQuery();
//        lqw.in(AppInfo::getAppId, appIds);
//        return appInfoMapper.selectList(lqw, RemoteAppInfo.class);
//    }
//
//    @Override
//    public List<RemoteAppInfo> getAppsByQuery(String query, Long appId) throws ServiceException {
//        LambdaQueryWrapper<AppInfo> lqw = Wrappers.lambdaQuery();
//        lqw.eq(appId != null, AppInfo::getAppId, appId);
//
//        if (StringUtils.isNotBlank(query)) {
//            lqw.like(AppInfo::getAppId, query)
//                .or().like(AppInfo::getAppName, query)
//                .or().like(AppInfo::getAppCode, query);
//        }
//
//        return appInfoMapper.selectList(lqw, RemoteAppInfo.class);
//    }
//
//    @Override
//    public String getApps(Long userId) {
//        LambdaQueryWrapper<AppUser> lqw = Wrappers.lambdaQuery();
//        lqw.eq(userId != null, AppUser::getUserId, userId);
//        List<AppUser> appInfos = appUserMapper.selectList(lqw);
//        if (CollUtil.isNotEmpty(appInfos)) {
//            return StreamUtils.join(appInfos, d -> Convert.toStr(d.getAppId()));
//        }
//        return null;
//    }
//
//}
