package com.lvpb.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@Schema(name = "用户信息")
public class UserResponse implements Serializable {

    /**
     * 用户编号
     */
    @Schema(description="用户编号")
    private Long id;

    /**
     * 用户名(账号)
     */
    @Schema(description="用户名(账号)")
    private String username;

    /**
     * 用户昵称
     */
    @Schema(description="用户昵称")
    private String nickname;

    /**
     * 头像地址
     */
    @Schema(description="头像地址")
    private String avatar;

    /**
     * 电话
     */
    @Schema(description="电话")
    private String phone;

    /**
     * 邮箱
     */
    @Schema(description="邮箱")
    private String email;

    /**
     * 角色(0:普通用户,1:管理员)
     */
    @Schema(description="角色(0:普通用户,1:管理员)")
    private Integer role;

    /**
     * 性别(0:未知,1:男,2:女)
     */
    @Schema(description="性别(0:未知,1:男,2:女)")
    private Integer gender;

    /**
     * 状态(0:正常,1:禁用)
     */
    @Schema(description="状态(0:正常,1:禁用)")
    private Integer status;

    /**
     * 创建时间
     */
    @Schema(description="创建时间")
    private Date createTime;
}
