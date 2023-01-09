package com.frankzhou.datastandard.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.frankzhou.datastandard.common.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "standard_language")
public class StandardLanguageDO extends BaseDO {
    private static final long serialVersionUID = -478951346L;

    @TableField("serial_no")
    private String serialNo;

    @TableField("name_cn")
    private String nameCn;

    @TableField("name_en")
    private String nameEn;

    @TableField("name_en_abbr")
    private String nameEnAbbr;

    @TableField("description")
    private String description;

    @TableField("standard_status")
    private String standardStatus;
}
