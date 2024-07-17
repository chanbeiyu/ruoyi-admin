package org.dromara.common.tenant.helper;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.baomidou.mybatisplus.core.plugins.IgnoreStrategy;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.constant.GlobalConstants;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.common.satoken.utils.LoginHelper;

import java.util.function.Supplier;

/**
 * APP助手
 *
 * @author Lion Li
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppHelper {

    private static final String DYNAMIC_APP_KEY = GlobalConstants.GLOBAL_REDIS_KEY + "dynamicApp";
    private static final ThreadLocal<Long> TEMP_DYNAMIC_APP = new TransmittableThreadLocal<>();

    /**
     * 开启忽略APP(开启后需手动调用 {@link #disableIgnore()} 关闭)
     */
    public static void enableIgnore() {
        InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).build());
    }

    /**
     * 关闭忽略APP
     */
    public static void disableIgnore() {
        InterceptorIgnoreHelper.clearIgnoreStrategy();
    }

    /**
     * 在忽略APP中执行
     *
     * @param handle 处理执行方法
     */
    public static void ignore(Runnable handle) {
        enableIgnore();
        try {
            handle.run();
        } finally {
            disableIgnore();
        }
    }

    /**
     * 在忽略APP中执行
     *
     * @param handle 处理执行方法
     */
    public static <T> T ignore(Supplier<T> handle) {
        enableIgnore();
        try {
            return handle.get();
        } finally {
            disableIgnore();
        }
    }

    /**
     * 设置动态APP(一直有效 需要手动清理)
     * <p>
     * 如果为未登录状态下 那么只在当前线程内生效
     */
    public static void setDynamic(Long appId) {
        if (!isLogin()) {
            TEMP_DYNAMIC_APP.set(appId);
            return;
        }
        clearDynamic();
        String cacheKey = DYNAMIC_APP_KEY + ":" + LoginHelper.getUserId();
        RedisUtils.setCacheObject(cacheKey, appId + "");
        SaHolder.getStorage().set(cacheKey, appId);
    }

    /**
     * 获取动态APP(一直有效 需要手动清理)
     * <p>
     * 如果为未登录状态下 那么只在当前线程内生效
     */
    public static Long getDynamic() {
        if (!isLogin()) {
            return TEMP_DYNAMIC_APP.get();
        }
        String cacheKey = DYNAMIC_APP_KEY + ":" + LoginHelper.getUserId();
        if (SaHolder.getStorage().has(cacheKey)) {
            return SaHolder.getStorage().getLong(cacheKey);
        }
        if (RedisUtils.hasKey(cacheKey)) {
            Long value = Long.valueOf(RedisUtils.getCacheObject(cacheKey));
            SaHolder.getStorage().set(cacheKey, value);
            return value;
        }
        return null;
    }

    /**
     * 清除动态APP
     */
    public static void clearDynamic() {
        if (!isLogin()) {
            TEMP_DYNAMIC_APP.remove();
            return;
        }
        String cacheKey = DYNAMIC_APP_KEY + ":" + LoginHelper.getUserId();
        RedisUtils.deleteObject(cacheKey);
        SaHolder.getStorage().delete(cacheKey);
    }

    /**
     * 在动态APP中执行
     *
     * @param handle 处理执行方法
     */
    public static void dynamic(Long appId, Runnable handle) {
        Long _appId = getAppId();
        setDynamic(appId);
        try {
            handle.run();
        } finally {
            clearDynamic();
            setDynamic(_appId);
        }
    }

    /**
     * 在动态APP中执行
     *
     * @param handle 处理执行方法
     */
    public static <T> T dynamic(Long appId, Supplier<T> handle) {
        Long _appId = getAppId();
        setDynamic(appId);
        try {
            return handle.get();
        } finally {
            clearDynamic();
            setDynamic(_appId);
        }
    }

    /**
     * 获取当前appId
     */
    public static Long getAppId() {
        return AppHelper.getDynamic();

    }

    private static boolean isLogin() {
        try {
            StpUtil.checkLogin();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
