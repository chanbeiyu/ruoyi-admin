package org.dromara.platform.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TradeApplicationRunner implements ApplicationRunner, DisposableBean {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("初始化 Trade 配置成功");
    }

    @Override
    public void destroy() throws Exception {

    }
}
