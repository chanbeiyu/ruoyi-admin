package org.dromara.platform.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 初始化 system 模块对应业务数据
 *
 * @author chanbeiyu
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AdminApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("初始化 Admin 配置成功");
    }

}
