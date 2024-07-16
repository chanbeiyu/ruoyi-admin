package org.dromara.common.mybatis.core.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class ModifyResult<R> {

    private boolean ok;
    private int count;
    private R data;
    private String msg;

    public ModifyResult(int count, R data) {
        this.ok = count > 0;
        this.data = data;
        this.count = count;
    }

    public ModifyResult(boolean ok, R data) {
        this.ok = ok;
        this.data = data;
        this.count = (data instanceof Collection) ? ((Collection<?>) data).size() : 0;
    }

    public ModifyResult(boolean ok, R data, String msg) {
        this.ok = ok;
        this.data = data;
        this.msg = msg;
        this.count = (data instanceof Collection) ? ((Collection<?>) data).size() : 0;
    }

    public ModifyResult(boolean ok, String msg) {
        this.ok = ok;
        this.msg = msg;
    }

    public ModifyResult(int count, String msg) {
        this.ok = count > 0;
        this.count = count;
        this.msg = msg;
    }
}
