package org.dromara.platform.translation;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.app.entity.AppInfo;
import org.dromara.basis.app.entity.AppSubscribe;
import org.dromara.basis.app.service.AppInfoService;
import org.dromara.basis.app.service.AppSubscribeService;
import org.dromara.basis.constant.TranslationConst;
import org.dromara.basis.member.entity.MemberInfo;
import org.dromara.basis.member.entity.MemberType;
import org.dromara.basis.member.service.MemberInfoService;
import org.dromara.basis.member.service.MemberTypeService;
import org.dromara.basis.social.entity.SocialNoticeType;
import org.dromara.basis.social.entity.SocialSubject;
import org.dromara.basis.social.entity.SocialTag;
import org.dromara.basis.social.service.SocialNoticeTypeService;
import org.dromara.basis.social.service.SocialSubjectService;
import org.dromara.basis.social.service.SocialTagService;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.common.translation.annotation.TranslationType;
import org.dromara.common.translation.core.TranslationInterface;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.function.Function;

import static org.dromara.basis.constant.TranslationConst.*;

/**
 * @author wy_peng_chen6
 */
@Component
@RequiredArgsConstructor
@TranslationType(type = TranslationConst.TYPE_KEY)
public class PlatformTranslation implements TranslationInterface<String> {

    private final AppInfoService appInfoService;
    private final MemberInfoService memberInfoService;
    private final MemberTypeService memberTypeService;
    private final AppSubscribeService appSubscribeService;

    private final SocialNoticeTypeService socialNoticeTypeService;
    private final SocialTagService socialTagService;
    private final SocialSubjectService socialSubjectService;

    @Override
    public String translation(Object key, String other) {
        switch (other) {
            case APP_INFO_NAME -> {
                return translation(key, APP_INFO_NAME, appInfoService, AppInfo::getAppName);
            }
            case APP_INFO_CODE -> {
                return translation(key, APP_INFO_CODE, appInfoService, AppInfo::getAppCode);
            }
            case APP_SUBSCRIBE_NAME -> {
                return translation(key, APP_SUBSCRIBE_NAME, appSubscribeService, AppSubscribe::getTitle);
            }
            case APP_SUBSCRIBE_CODE -> {
                return translation(key, APP_SUBSCRIBE_CODE, appSubscribeService, AppSubscribe::getCode);
            }
            case MEMBER_INFO_NAME -> {
                return translation(key, MEMBER_TYPE_NAME, memberInfoService, MemberInfo::getNickName);
            }
            case MEMBER_TYPE_NAME -> {
                return translation(key, MEMBER_INFO_NAME, memberTypeService, MemberType::getTypeName);
            }
            case SOCIAL_NOTICE_TYPE_NAME -> {
                return translation(key, SOCIAL_NOTICE_TYPE_NAME, socialNoticeTypeService, SocialNoticeType::getNoticeTypeName);
            }
            case SOCIAL_TAG_NAME -> {
                return translation(key, SOCIAL_TAG_NAME, socialTagService, SocialTag::getTagName);
            }
            case SOCIAL_SUBJECT_NAME -> {
                return translation(key, SOCIAL_SUBJECT_NAME, socialSubjectService, SocialSubject::getSubjectName);
            }
        }
        return null;
    }


    private <E> String translation(Object key, String suffix, IBaseService<E, ?> baseService, Function<E, String> function) {
        String rkey = TYPE_KEY + suffix;
        String cacheValue = RedisUtils.getCacheMapValue(rkey, key.toString());
        if (StrUtil.isBlank(cacheValue)) {
            E entity = baseService.selectById(key.toString());
            if (entity != null) {
                String value = function.apply(entity);
                RedisUtils.setCacheMapValue(rkey, key.toString(), value);
                RedisUtils.expire(rkey, Duration.ofMinutes(60));
                return value;
            }
        } else {
            return cacheValue;
        }
        return null;
    }

}
