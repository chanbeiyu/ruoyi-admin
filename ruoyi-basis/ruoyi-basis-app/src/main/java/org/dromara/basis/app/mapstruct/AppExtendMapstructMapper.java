package org.dromara.basis.app.mapstruct;

import com.alibaba.fastjson2.JSON;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 用户信息转换器
 *
 * @author chanbeiyu
 */
@Component
public class AppExtendMapstructMapper {

    public List<Object> stringToList(String str) {
        if (JSON.isValidArray(str)) {
            return JSON.parseArray(str);
        }
        return null;
    }

    public String listToString(List<Object> list) {
        if (Objects.nonNull(list)) {
            return JSON.toJSONString(list);
        }
        return null;
    }

    public Map<String, Object> stringToMap(String str) {
        if (JSON.isValidObject(str)) {
            return JSON.parseObject(str);
        }
        return null;
    }

    public String mapToString(Map<String, Object> map) {
        if (Objects.nonNull(map)) {
            return JSON.toJSONString(map);
        }
        return null;
    }

}
