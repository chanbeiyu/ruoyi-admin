package org.dromara.platform.translation;

import lombok.AllArgsConstructor;
import org.dromara.common.translation.annotation.TranslationType;
import org.dromara.common.translation.core.TranslationInterface;
import org.dromara.platform.service.app.AppInfoService;
import org.springframework.stereotype.Component;

/**
 * @author wy_peng_chen6
 */
@Component
@AllArgsConstructor
@TranslationType(type = ThoughtsTranslation.key)
public class ThoughtsTranslation implements TranslationInterface<String> {

    public static final String key = "THOUGHTS_ID_TO_NAME";

    private AppInfoService appInfoService;

    @Override
    public String translation(Object key, String other) {
        switch (other) {
            case Other.APP -> {
                return appInfoService.queryById(Long.parseLong(key.toString())).getAppName();
            }
            default -> {
                return null;
            }
        }
    }

    public interface Other {
        String APP = "app";
    }

}
