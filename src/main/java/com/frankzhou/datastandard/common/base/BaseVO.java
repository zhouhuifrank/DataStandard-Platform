package com.frankzhou.datastandard.common.base;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseVO implements Serializable {
    private static final long serialVersionUID = 27637813L;

    protected Integer id;
}
