package com.lvpb.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lvpb.common.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 系统用户表
 */

@Schema(description="系统用户表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "`system_user`")
public class User extends BaseEntity {
    /**
     * 用户编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description="用户编号")
    private Long id;

    /**
     * 用户名(账号)
     */
    @TableField(value = "username")
    @Schema(description="用户名(账号)")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    @Schema(description="密码")
    private String password;

    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    @Schema(description="用户昵称")
    private String nickname;

    /**
     * 头像地址
     */
    @TableField(value = "avatar")
    @Schema(description="头像地址")
    private String avatar;

    /**
     * 电话
     */
    @TableField(value = "phone")
    @Schema(description="电话")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @Schema(description="邮箱")
    private String email;

    /**
     * 角色(0:普通用户,1:管理员)
     */
    @TableField(value = "role")
    @Schema(description="角色(0:普通用户,1:管理员)")
    private Integer role;

    /**
     * 性别(0:未知,1:男,2:女)
     */
    @TableField(value = "gender")
    @Schema(description="性别(0:未知,1:男,2:女)")
    private Integer gender;

    /**
     * 状态(0:正常,1:禁用)
     */
    @TableField(value = "`status`")
    @Schema(description="状态(0:正常,1:禁用)")
    private Integer status;
}