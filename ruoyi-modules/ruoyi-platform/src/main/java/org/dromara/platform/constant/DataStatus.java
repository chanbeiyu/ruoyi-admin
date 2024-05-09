package org.dromara.platform.constant;

/**
 * 业务操作类型
 *
 * @author ruoyi
 */
public enum DataStatus {

    /**
     * 草稿
     */
    DRAFT(0),

    /**
     * 发布
     */
    PUBLISH(1),

    /**
     * 锁定
     */
    LOCK(2),

    /**
     * 解锁
     */
    UNLOCK(0),

    /**
     * 删除
     */
    DELETE(3),
    ;

    public final int status;

    DataStatus(int status) {
        this.status = status;
    }
}
