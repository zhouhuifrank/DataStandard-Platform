package com.frankzhou.datastandard.common.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseQueryDTO extends BaseDTO {
    private static final long serialVersionUID = 78762442L;

    /**
     * 数据日期
     */
    protected LocalDateTime dataDate;

    /**
     * 数据开始日期
     */
    protected LocalDateTime startDate;

    /**
     * 数据结束日期
     */
    protected LocalDateTime endDate;
}
