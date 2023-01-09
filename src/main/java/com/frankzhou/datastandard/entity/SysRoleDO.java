package com.frankzhou.datastandard.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.frankzhou.datastandard.common.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "sys_role")
public class SysRoleDO extends BaseDO {

    private static final long serialVersionUID = 213891L;

    @TableField("role_id")
    private Integer roleId;

    @TableField("role_name")
    private String roleName;

    @TableField("role_key")
    private String roleKey;

    private Set<String> permissions;
}
