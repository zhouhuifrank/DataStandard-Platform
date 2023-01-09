package com.frankzhou.datastandard.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.frankzhou.datastandard.common.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "sys_user")
public class SysUserDO extends BaseDO {

    private static final long serialVersionUID = 273912L;

    @TableField("user_id")
    private Integer userId;

    @TableField("user_name")
    private String userName;

    @TableField("nick_name")
    private String nickName;

    @TableField("password")
    private String password;

    @TableField("gender")
    private String gender;

    @TableField("email")
    private String email;

    @TableField("phone_number")
    private String phoneNumber;

    // 与sys_role关联
    @TableField("role_id")
    private Integer roleId;
}
