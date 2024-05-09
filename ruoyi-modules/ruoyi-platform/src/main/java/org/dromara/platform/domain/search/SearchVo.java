package org.dromara.platform.domain.search;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 应用信息视图对象 social_app
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long value;
    private String label;
    private String code;

    @JsonIgnore
    private Long parentValue;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> extend;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SearchVo> children;

    public SearchVo putExtend(String key, Object obj) {
        if (extend == null) extend = new HashMap<>();
        extend.put(key, obj);
        return this;
    }

}
