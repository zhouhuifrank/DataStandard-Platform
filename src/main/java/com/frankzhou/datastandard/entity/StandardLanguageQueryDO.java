package com.frankzhou.datastandard.entity;

import com.frankzhou.datastandard.common.base.BasePageQueryDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StandardLanguageQueryDO extends BasePageQueryDO {

    private static final long serialVersionUID = 237148219L;

    private String serialNo;

    private String nameCn;

    private String nameEn;

    private String nameEnAbbr;

    private String description;

    private String standardStatus;

    private String[] ids;

    private String[] serialList;
}
