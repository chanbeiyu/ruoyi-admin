package org.dromara.platform.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.platform.constant.RedisKey;
import org.dromara.platform.domain.SocialNoticeType;
import org.dromara.platform.domain.SocialSubject;
import org.dromara.platform.domain.SocialTag;
import org.dromara.platform.mapper.SocialNoticeTypeMapper;
import org.dromara.platform.mapper.SocialSubjectMapper;
import org.dromara.platform.mapper.SocialTagMapper;
import org.dromara.common.redis.utils.CacheUtils;
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
public class SocialApplicationRunner implements ApplicationRunner, DisposableBean {

    private final SocialNoticeTypeMapper socialNoticeTypeMapper;
    private final SocialSubjectMapper socialSubjectMapper;
    private final SocialTagMapper socialTagMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initSocialMaping();
        log.info("初始化 initSocialMaping 配置成功");
    }

    private void initSocialMaping() {
        List<SocialNoticeType> socialNoticeTypes = socialNoticeTypeMapper.selectList();
        List<SocialSubject> socialSubjects = socialSubjectMapper.selectList();
        List<SocialTag> socialTags = socialTagMapper.selectList();

        socialNoticeTypes.forEach(o -> {
            CacheUtils.put(RedisKey.SOCIAL_NOTICTYPE_ID_NAME, o.getNoticeTypeId(), o.getNoticeTypeName());
            CacheUtils.put(RedisKey.SOCIAL_NOTICTYPE_CODE_NAME, o.getNoticeTypeCode(), o.getNoticeTypeName());
        });
        socialSubjects.forEach(o -> {
            CacheUtils.put(RedisKey.SOCIAL_SUBJECT_ID_NAME, o.getSubjectId(), o.getSubjectName());
            CacheUtils.put(RedisKey.SOCIAL_SUBJECT_CODE_NAME, o.getSubjectCode(), o.getSubjectName());
        });
        socialTags.forEach(o -> {
            CacheUtils.put(RedisKey.SOCIAL_TAG_ID_NAME, o.getTagId(), o.getTagName());
            CacheUtils.put(RedisKey.SOCIAL_TAG_CODE_NAME, o.getTagCode(), o.getTagName());
        });
    }

    @Override
    public void destroy() throws Exception {
        CacheUtils.clear(RedisKey.SOCIAL_NOTICTYPE_ID_NAME);
        CacheUtils.clear(RedisKey.SOCIAL_NOTICTYPE_CODE_NAME);
        CacheUtils.clear(RedisKey.SOCIAL_SUBJECT_ID_NAME);
        CacheUtils.clear(RedisKey.SOCIAL_SUBJECT_CODE_NAME);
        CacheUtils.clear(RedisKey.SOCIAL_TAG_ID_NAME);
        CacheUtils.clear(RedisKey.SOCIAL_TAG_CODE_NAME);
    }
}
