package org.dromara.platform.domain.thoughts;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * 思绪专题关联信息对象 thot_channel_thought
 *
 * @author chanbeiyu
 * @date 2023-07-06
 */
@Data
@TableName("thot_channel_thought")
public class ThotChannelThought {

    @TableId(value = "id")
    private Long id;

    /**
     * 频道id
     */
    private Long channelId;

    /**
     * 思绪ID
     */
    private Long thoughtId;


}
