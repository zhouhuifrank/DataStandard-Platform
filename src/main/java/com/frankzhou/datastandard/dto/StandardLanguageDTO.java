package com.frankzhou.datastandard.dto;

import com.frankzhou.datastandard.common.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@Getter
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "标准分词实体类",description = "标准分词所包含的具体信息")
public class StandardLanguageDTO extends BaseDTO {
    private static final long serialVersionUID = 8475981L;

    @ApiModelProperty(value = "标准分词编号",example = "W000001",dataType = "String")
    private String serialNo;

    @ApiModelProperty(value = "标准分词中文名",example = "利润",dataType = "String")
    private String nameCn;

    @ApiModelProperty(value = "标准分词英文名",example = "Profit",dataType = "String")
    private String nameEn;

    @ApiModelProperty(value = "标准分词英文缩写",example = "Profit",dataType = "String")
    private String nameEnAbbr;

    @ApiModelProperty(value = "标准分词业务描述",example = "用于描述各种类型的利润",dataType = "String")
    private String description;

    @ApiModelProperty(value = "标准分词状态",example = "NORMAL",dataType = "String")
    private String standardStatus;
}
