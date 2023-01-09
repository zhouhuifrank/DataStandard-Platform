package com.frankzhou.datastandard.common.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BasePageQueryDO extends BaseQueryDO {
    private static final long serialVersionUID = 2839813763L;

    protected Integer startRow;

    protected Integer endRow;

    protected Integer startPage;

    protected Integer limit;

    protected String orderBy;

    protected String sort;
}
