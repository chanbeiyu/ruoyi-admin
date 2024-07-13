//package org.dromara.platform.translation;
//
//import lombok.RequiredArgsConstructor;
//import org.dromara.basis.app.mapper.AppInfoMapper;
//import org.dromara.basis.member.mapper.MemberInfoMapper;
//import org.dromara.basis.member.mapper.MemberTypeMapper;
//import org.dromara.basis.social.mapper.SocialNoticeTypeMapper;
//import org.dromara.basis.social.mapper.SocialSubjectMapper;
//import org.dromara.basis.social.mapper.SocialTagMapper;
//import org.dromara.common.redis.utils.RedisUtils;
//import org.dromara.common.translation.annotation.TranslationType;
//import org.dromara.common.translation.core.TranslationInterface;
//import org.dromara.platform.constant.RedisKey;
//import org.springframework.stereotype.Component;
//
///**
// * @author wy_peng_chen6
// */
//@Component
//@RequiredArgsConstructor
//@TranslationType(type = ApptwTranslation.key)
//public class ApptwTranslation implements TranslationInterface<String> {
//
//    public static final String key = "APP_ID_TO_NAME";
//
//    private final AppInfoMapper appInfoMapper;
//    private final MemberInfoMapper memberInfoMapper;
//    private final MemberTypeMapper memberTypeMapper;
//
//    private final SocialNoticeTypeMapper socialNoticeTypeMapper;
//    private final SocialTagMapper socialTagMapper;
//    private final SocialSubjectMapper socialSubjectMapper;
//
//    @Override
//    public String translation(Object key, String other) {
//        switch (other) {
//            case Other.APP_NAME -> {
//                return RedisUtils.getCacheMapValue(RedisKey.APP_ID_NAME, String.valueOf(key));
//            }
//            case Other.APP_CODE -> {
//                return RedisUtils.getCacheMapValue(RedisKey.APP_ID_CODE, String.valueOf(key));
//            }
//            case Other.MEMBER_INFO -> {
//                //return CacheUtils.get(RedisKey.MEMBER_INFO_ID_NAME, key);
//                return memberInfoMapper.selectById(Long.parseLong(key.toString())).getNickName();
//            }
//            case Other.MEMBER_TYPE -> {
//                //return CacheUtils.get(RedisKey.MEMBER_TYPE_ID_NAME, key);
//                return memberTypeMapper.selectById(Long.parseLong(key.toString())).getTypeName();
//            }
//            case Other.SOCIAL_NOTICE -> {
//                // return CacheUtils.get(RedisKey.SOCIAL_NOTICTYPE_ID_NAME, key);
//                return socialNoticeTypeMapper.selectById(Long.parseLong(key.toString())).getNoticeTypeName();
//            }
//            case Other.SOCIAL_TAG -> {
//                // return CacheUtils.get(RedisKey.SOCIAL_TAG_ID_NAME, key);
//                return socialTagMapper.selectById(Long.parseLong(key.toString())).getTagName();
//            }
//            case Other.SOCIAL_SUBJECT -> {
//                // return CacheUtils.get(RedisKey.SOCIAL_SUBJECT_ID_NAME, key);
//                return socialSubjectMapper.selectById(Long.parseLong(key.toString())).getSubjectName();
//            }
//            default -> {
//                return null;
//            }
//        }
//    }
//
//    public interface Other {
//        String APP_CODE = "app_code";
//        String APP_NAME = "app_name";
//        String MEMBER_INFO = "member_info";
//        String MEMBER_TYPE = "member_type";
//        String SOCIAL_SUBJECT = "subject";
//        String SOCIAL_NOTICE = "notice";
//        String SOCIAL_TAG = "tag";
//    }
//
//}
