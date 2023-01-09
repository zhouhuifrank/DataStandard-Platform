package com.frankzhou.datastandard.vo;

import com.frankzhou.datastandard.common.base.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StandardLanguageVO  extends BaseVO {

    private static final long serialVersionUID = 8475961L;

    private String serialNo;

    private String nameCn;

    private String nameEn;

    private String nameEnAbbr;

    private String description;

    private String standardStatus;
}
