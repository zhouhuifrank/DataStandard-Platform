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
@ApiModel(value = "用户注册请求类",description = "用户注册所需要的信息")
public class UserRegisterDTO extends BaseDTO {

    private static final long serialVersionUID = 2131235L;

    @ApiModelProperty(value = "用户名",example = "frankzhou",dataType = "String")
    private String userName;

    @ApiModelProperty(value = "昵称",example = "zhouzhou",dataType = "String")
    private String nickName;

    @ApiModelProperty(value = "密码",example = "123456",dataType = "String")
    private String password;

    @ApiModelProperty(value = "性别",example = "male",dataType = "String")
    private String gender;

    @ApiModelProperty(value = "邮箱",example = "frankzhou@fdu.edu.cn",dataType = "String")
    private String email;

    @ApiModelProperty(value = "手机号",example = "13818924327",dataType = "String")
    private String phoneNumber;
}
