package org.dromara.platform.search;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.app.entity.AppInfo;
import org.dromara.basis.app.entity.AppSubject;
import org.dromara.basis.app.mapper.AppInfoMapper;
import org.dromara.basis.app.mapper.AppSubjectMapper;
import org.dromara.basis.member.entity.MemberInfo;
import org.dromara.basis.member.entity.MemberType;
import org.dromara.basis.member.mapper.MemberInfoMapper;
import org.dromara.basis.member.mapper.MemberTypeMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.basis.constant.SearchVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 搜索信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-03
 */
@Service
@RequiredArgsConstructor
public class SearchService {

    private final AppInfoMapper appInfoMapper;
    private final MemberInfoMapper memberInfoMapper;
    private final MemberTypeMapper memberTypeMapper;
    private final AppSubjectMapper appSubjectMapper;

    public TableDataInfo<SearchVo> searchAppList(String query, Long appId) {
        return TableDataInfo.build(searchApp(query, appId));
    }


    public TableDataInfo<SearchVo> searchSubjectList(String query, Long appId, boolean cascade) {
        List<SearchVo> subjects = searchSubject(query, appId);
        if (cascade) {
            List<SearchVo> apps = searchApp(null, appId);
            Map<String, List<SearchVo>> subjectMap = subjects.stream()
                .collect(Collectors.groupingBy(SearchVo::getParentValue));
            apps.forEach(app -> {
                app.setChildren(subjectMap.get(app.getValue()));
            });
            return TableDataInfo.build(apps);
        }
        return TableDataInfo.build(subjects);
    }

    public TableDataInfo<SearchVo> searchMemberInfo(String query, Long appId, boolean cascade) {
        List<SearchVo> memberInfos = searchMemberInfo(query, appId);
        if (cascade) {
            List<SearchVo> apps = searchApp(null, appId);
            Map<String, List<SearchVo>> memberInfoMap = memberInfos.stream()
                .collect(Collectors.groupingBy(SearchVo::getParentValue));
            apps.forEach(app -> {
                app.setChildren(memberInfoMap.get(app.getValue()));
            });
            return TableDataInfo.build(apps);
        }
        return TableDataInfo.build(memberInfos);
    }

    public TableDataInfo<SearchVo> searchMemberType(String query, Long appId, boolean cascade) {
        List<SearchVo> memberTypes = searchMemberType(query, appId);
        if (cascade) {
            List<SearchVo> apps = searchApp(null, appId);
            Map<String, List<SearchVo>> memberTypeMap = memberTypes.stream()
                .collect(Collectors.groupingBy(SearchVo::getParentValue));
            apps.forEach(app -> {
                app.setChildren(memberTypeMap.get(app.getValue()));
            });
            return TableDataInfo.build(apps);
        }
        return TableDataInfo.build(memberTypes);
    }

    private SearchVo searchApp(Long appId) {
        return appInfoMapper.selectById(appId, appInfo -> SearchVo.builder()
            .value(appInfo.getAppId() + "")
            .code(appInfo.getAppCode())
            .label(appInfo.getAppName())
            .build());
    }

    private List<SearchVo> searchApp(List<Long> appIds) {
        LambdaQueryWrapper<AppInfo> lqw = Wrappers.lambdaQuery();
        lqw.in(AppInfo::getAppId, appIds);
        return appInfoMapper.selectList(lqw, appInfo -> {
            return SearchVo.builder()
                .value(appInfo.getAppId() + "")
                .label(appInfo.getAppName())
                .code(appInfo.getAppCode())
                .build();
        });
    }

    private List<SearchVo> searchApp(String query, Long appId) {
        LambdaQueryWrapper<AppInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(appId != null, AppInfo::getAppId, appId);

        if (StringUtils.isNotBlank(query)) {
            lqw.like(AppInfo::getAppId, query)
                .or().like(AppInfo::getAppName, query)
                .or().like(AppInfo::getAppCode, query);
        }

        return appInfoMapper.selectList(lqw, appInfo -> {
            return SearchVo.builder()
                .value(appInfo.getAppId() + "")
                .code(appInfo.getAppCode())
                .label(appInfo.getAppName())
                .build();
        });
    }

    private List<SearchVo> searchSubject(String query, Long appId) {

        LambdaQueryWrapper<AppSubject> lqw = Wrappers.lambdaQuery();
        lqw.eq(appId != null, AppSubject::getAppId, appId);

        if (StringUtils.isNotBlank(query)) {
            lqw.and(i -> i.like(AppSubject::getSubjectId, query)
                .or().like(AppSubject::getSubjectName, query)
                .or().like(AppSubject::getSubjectCode, query));
        }

        return appSubjectMapper.selectList(lqw, appSubject -> {
            return SearchVo.builder().value(appSubject.getSubjectId() + "")
                .code(appSubject.getSubjectCode())
                .label(appSubject.getSubjectName())
                .parentValue(appSubject.getAppId() + "").build();
        });
    }

    private List<SearchVo> searchMemberType(String query, Long appId) {

        LambdaQueryWrapper<MemberType> lqw = Wrappers.lambdaQuery();
        lqw.eq(appId != null, MemberType::getAppId, appId);

        if (StringUtils.isNotBlank(query)) {
            lqw.and(i -> i.like(MemberType::getTypeId, query)
                .or().like(MemberType::getTypeCode, query)
                .or().like(MemberType::getTypeName, query));
        }

        return memberTypeMapper.selectList(lqw, memberType -> {
            return SearchVo.builder()
                .value(memberType.getTypeId() + "")
                .code(memberType.getTypeCode())
                .label(memberType.getTypeName())
                .parentValue(memberType.getAppId() + "")
                .build();
        });
    }

    private List<SearchVo> searchMemberInfo(String query, Long appId) {

        LambdaQueryWrapper<MemberInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(appId != null, MemberInfo::getAppId, appId);

        if (StringUtils.isNotBlank(query)) {
            lqw.and(i -> i.like(MemberInfo::getMemberId, query)
                .or().like(MemberInfo::getUnionId, query)
                .or().like(MemberInfo::getNickName, query));
        }

        return memberInfoMapper.selectList(lqw, memberInfo -> {
            return SearchVo.builder()
                .value(memberInfo.getMemberId() + "")
                .code(memberInfo.getUnionId())
                .label(memberInfo.getNickName())
                .parentValue(memberInfo.getAppId() + "")
                .build();
        });
    }

}
