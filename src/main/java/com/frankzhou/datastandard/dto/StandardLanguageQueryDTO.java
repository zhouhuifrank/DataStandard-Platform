package com.frankzhou.datastandard.dto;

import com.frankzhou.datastandard.common.base.BasePageQueryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class StandardLanguageQueryDTO extends BasePageQueryDTO {

    private static final long serialVersionUID = 237183719L;

    private String serialNo;

    private String nameCn;

    private String nameEn;

    private String nameEnAbbr;

    private String description;

    private String standardStatus;

    private String[] ids;

    private String[] serialList;
}
