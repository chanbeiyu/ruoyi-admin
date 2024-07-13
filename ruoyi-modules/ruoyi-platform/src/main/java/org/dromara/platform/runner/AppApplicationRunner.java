package org.dromara.platform.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.basis.app.entity.AppInfo;
import org.dromara.basis.app.entity.AppSubject;
import org.dromara.basis.app.entity.AppTag;
import org.dromara.basis.app.mapper.AppInfoMapper;
import org.dromara.basis.app.mapper.AppSubjectMapper;
import org.dromara.basis.app.mapper.AppTagMapper;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.platform.constant.RedisKey;
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
    private final AppSubjectMapper appSubjectMapper;
    private final AppTagMapper appTagMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initAppMapping();
        log.info("初始化 initAppMapping 配置成功");
    }

    private void initAppMapping() {
        List<AppInfo> appInfos = appInfoMapper.selectList();
        List<AppSubject> appSubjects = appSubjectMapper.selectList();
        List<AppTag> appTags = appTagMapper.selectList();
        appInfos.forEach(o -> {
            RedisUtils.setCacheMapValue(RedisKey.APP_ID_CODE, o.getAppId() + "", o.getAppCode());
            RedisUtils.setCacheMapValue(RedisKey.APP_ID_NAME, o.getAppId() + "", o.getAppName());
            RedisUtils.setCacheMapValue(RedisKey.APP_CODE_NAME, o.getAppCode(), o.getAppName());
        });
        appSubjects.forEach(o -> {
            RedisUtils.setCacheMapValue(RedisKey.APP_SUBJECT_ID_CODE, o.getSubjectId() + "", o.getSubjectCode());
            RedisUtils.setCacheMapValue(RedisKey.APP_SUBJECT_ID_NAME, o.getSubjectId() + "", o.getSubjectName());
            RedisUtils.setCacheMapValue(RedisKey.APP_SUBJECT_CODE_NAME, o.getSubjectCode(), o.getSubjectName());
        });
        appTags.forEach(o -> {
            RedisUtils.setCacheMapValue(RedisKey.APP_TAG_ID_CODE, o.getTagId() + "", o.getTagCode());
            RedisUtils.setCacheMapValue(RedisKey.APP_TAG_ID_NAME, o.getTagId() + "", o.getTagName());
            RedisUtils.setCacheMapValue(RedisKey.APP_TAG_CODE_NAME, o.getTagCode(), o.getTagName());
        });
    }

    @Override
    public void destroy() {
        RedisUtils.deleteObject(RedisKey.APP_TAG_ID_CODE);
        RedisUtils.deleteObject(RedisKey.APP_TAG_ID_NAME);
        RedisUtils.deleteObject(RedisKey.APP_TAG_CODE_NAME);
    }

}
