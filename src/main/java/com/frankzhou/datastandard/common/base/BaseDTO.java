package com.frankzhou.datastandard.common.base;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public abstract class BaseDTO implements Serializable {
    private static final long serialVersionUID = 78273823L;

    /**
     * 主键Id
     */
    protected Integer id;

    /**
     * 数据创建时间
     */
    protected LocalDateTime createTime;

    /**
     * 数据修改时间
     */
    protected LocalDateTime updateTime;

    /**
     * 数据状态
     */
    protected String status;
}
