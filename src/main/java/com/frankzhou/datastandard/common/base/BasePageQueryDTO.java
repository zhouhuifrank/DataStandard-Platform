package com.frankzhou.datastandard.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@Getter
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "BaseQueryDTO对象", description = "分页查询的queryDTO直接继承")
public class BasePageQueryDTO extends BaseQueryDTO {

    private static final long serialVersionUID = 28398193L;

    @ApiModelProperty(value = "开始页",example = "1",dataType = "Integer")
    protected Integer startPage = 1;

    @ApiModelProperty(value = "分页查询数量",example = "10",dataType = "Integer")
    protected Integer limit = 10;

    @ApiModelProperty(value = "排序字段",example = "createTime",dataType = "String")
    protected String orderBy;

    @ApiModelProperty(value = "排序类型:升序/降序",example = "asc/desc",dataType = "String")
    protected String sort;
}
