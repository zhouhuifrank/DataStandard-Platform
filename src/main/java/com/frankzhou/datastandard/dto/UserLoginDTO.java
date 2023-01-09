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
@ApiModel(value = "用户登录请求类",description = "用户登录所需要的信息")
public class UserLoginDTO extends BaseDTO {

    private static final long serialVersionUID = 2137812L;

    @ApiModelProperty(value = "登录模式",example = "pwd用户名密码登录/code手机验证码登录",dataType = "String")
    private String mode;

    @ApiModelProperty(value = "用户名",example = "frankzhou",dataType = "String")
    private String userName;

    @ApiModelProperty(value = "密码",example = "123456",dataType = "String")
    private String password;

    @ApiModelProperty(value = "手机号",example = "13818904452",dataType = "String")
    private String phoneNum;

    @ApiModelProperty(value = "验证码",example = "wo34a4",dataType = "String")
    private String code;
}
