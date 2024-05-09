package org.dromara.platform.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.platform.constant.RedisKey;
import org.dromara.platform.domain.AppInfo;
import org.dromara.common.redis.utils.CacheUtils;
import org.dromara.platform.mapper.AppInfoMapper;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 初始化 system 模块对应业务数据
 *
 * @author chanbeiyu
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AppApplicationRunner implements ApplicationRunner, DisposableBean {

    private final AppInfoMapper appInfoMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initAppMapping();
        log.info("初始化 initAppMapping 配置成功");
    }

    private void initAppMapping() {
        List<AppInfo> appInfos = appInfoMapper.selectList();
        appInfos.forEach(o -> {
            CacheUtils.put(RedisKey.APP_ID_NAME, o.getAppId(), o.getAppName());
            CacheUtils.put(RedisKey.APP_CODE_NAME, o.getAppCode(), o.getAppName());
        });
    }

    @Override
    public void destroy() {
        CacheUtils.clear(RedisKey.APP_ID_NAME);
        CacheUtils.clear(RedisKey.APP_CODE_NAME);
    }

}
