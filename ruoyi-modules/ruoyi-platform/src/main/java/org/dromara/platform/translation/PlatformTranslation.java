package org.dromara.platform.translation;

import lombok.RequiredArgsConstructor;
import org.dromara.basis.app.entity.AppInfo;
import org.dromara.basis.app.mapper.AppInfoMapper;
import org.dromara.basis.constant.TranslationConst;
import org.dromara.basis.member.entity.MemberInfo;
import org.dromara.basis.member.entity.MemberType;
import org.dromara.basis.member.mapper.MemberInfoMapper;
import org.dromara.basis.member.mapper.MemberTypeMapper;
import org.dromara.basis.social.entity.SocialNoticeType;
import org.dromara.basis.social.entity.SocialSubject;
import org.dromara.basis.social.entity.SocialTag;
import org.dromara.basis.social.mapper.SocialNoticeTypeMapper;
import org.dromara.basis.social.mapper.SocialSubjectMapper;
import org.dromara.basis.social.mapper.SocialTagMapper;
import org.dromara.common.translation.annotation.TranslationType;
import org.dromara.common.translation.core.TranslationInterface;
import org.springframework.stereotype.Component;

/**
 * @author wy_peng_chen6
 */
@Component
@RequiredArgsConstructor
@TranslationType(type = TranslationConst.key)
public class PlatformTranslation implements TranslationInterface<String> {

    private final AppInfoMapper appInfoMapper;
    private final MemberInfoMapper memberInfoMapper;
    private final MemberTypeMapper memberTypeMapper;

    private final SocialNoticeTypeMapper socialNoticeTypeMapper;
    private final SocialTagMapper socialTagMapper;
    private final SocialSubjectMapper socialSubjectMapper;

    @Override
    public String translation(Object key, String other) {
        switch (other) {
            case TranslationConst.APP_NAME -> {
                AppInfo appInfo = appInfoMapper.selectById(Long.parseLong(key.toString()));
                if (appInfo != null) {
                    return appInfo.getAppName();
                }
            }
            case TranslationConst.APP_CODE -> {
                AppInfo appInfo = appInfoMapper.selectById(Long.parseLong(key.toString()));
                if (appInfo != null) {
                    return appInfo.getAppCode();
                }
            }
            case TranslationConst.MEMBER_INFO -> {
                MemberInfo memberInfo = memberInfoMapper.selectById(Long.parseLong(key.toString()));
                if (memberInfo != null) {
                    return memberInfo.getNickName();
                }
            }
            case TranslationConst.MEMBER_TYPE -> {
                MemberType memberType = memberTypeMapper.selectById(Long.parseLong(key.toString()));
                if (memberType != null) {
                    return memberType.getTypeName();
                }
            }
            case TranslationConst.SOCIAL_NOTICE -> {
                SocialNoticeType socialNoticeType = socialNoticeTypeMapper.selectById(Long.parseLong(key.toString()));
                if (socialNoticeType != null) {
                    return socialNoticeType.getNoticeTypeName();
                }
            }
            case TranslationConst.SOCIAL_TAG -> {
                SocialTag socialTag = socialTagMapper.selectById(Long.parseLong(key.toString()));
                if (socialTag != null) {
                    return socialTag.getTagName();
                }
            }
            case TranslationConst.SOCIAL_SUBJECT -> {
                SocialSubject socialSubject = socialSubjectMapper.selectById(Long.parseLong(key.toString()));
                if (socialSubject != null) {
                    return socialSubject.getSubjectName();
                }
            }
        }
        return null;
    }

}
