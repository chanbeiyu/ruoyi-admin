package org.dromara.platform.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.basis.social.entity.SocialNoticeType;
import org.dromara.basis.social.entity.SocialSubject;
import org.dromara.basis.social.entity.SocialTag;
import org.dromara.basis.social.mapper.SocialNoticeTypeMapper;
import org.dromara.basis.social.mapper.SocialSubjectMapper;
import org.dromara.basis.social.mapper.SocialTagMapper;
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
//            RedisUtils.setCacheMapValue(RedisKey.SOCIAL_NOTICTYPE_ID_NAME, o.getNoticeTypeId() + "", o.getNoticeTypeName());
//            RedisUtils.setCacheMapValue(RedisKey.SOCIAL_NOTICTYPE_CODE_NAME, o.getNoticeTypeCode(), o.getNoticeTypeName());
        });
        socialSubjects.forEach(o -> {
//            RedisUtils.setCacheMapValue(RedisKey.SOCIAL_SUBJECT_ID_NAME, o.getSubjectId() + "", o.getSubjectName());
//            RedisUtils.setCacheMapValue(RedisKey.SOCIAL_SUBJECT_CODE_NAME, o.getSubjectCode(), o.getSubjectName());
        });
        socialTags.forEach(o -> {
//            RedisUtils.setCacheMapValue(RedisKey.SOCIAL_TAG_ID_NAME, o.getTagId() + "", o.getTagName());
//            RedisUtils.setCacheMapValue(RedisKey.SOCIAL_TAG_CODE_NAME, o.getTagCode(), o.getTagName());
        });
    }

    @Override
    public void destroy() throws Exception {
//        RedisUtils.deleteObject(RedisKey.SOCIAL_NOTICTYPE_ID_NAME);
//        RedisUtils.deleteObject(RedisKey.SOCIAL_NOTICTYPE_CODE_NAME);
//        RedisUtils.deleteObject(RedisKey.SOCIAL_SUBJECT_ID_NAME);
//        RedisUtils.deleteObject(RedisKey.SOCIAL_SUBJECT_CODE_NAME);
//        RedisUtils.deleteObject(RedisKey.SOCIAL_TAG_ID_NAME);
//        RedisUtils.deleteObject(RedisKey.SOCIAL_TAG_CODE_NAME);
    }
}
