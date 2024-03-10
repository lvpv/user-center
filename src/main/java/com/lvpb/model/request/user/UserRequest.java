package com.lvpb.model.request.user;

import com.lvpb.validation.EnumValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Data
public class UserRequest implements Serializable {

    /**
     * 用户名(账号)
     */
    @Schema(description = "用户名",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "用户名不能为空")
    @Length(min = 4,max = 15,message = "用户名长度不能小于4位大于15位")
    private String username;

    /**
     * 用户昵称
     */
    @Schema(description="用户昵称")
    @Length(min = 1,max = 15,message = "昵称长度不能小于1位大于15位")
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
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 角色(0:普通用户,1:管理员)
     */
    @Schema(description="角色(0:普通用户,1:管理员)")
    @EnumValue(intValues = {0,1},message = "用户角色只能为0或1")
    private Integer role;

    /**
     * 性别(0:未知,1:男,2:女)
     */
    @Schema(description="性别")
    @EnumValue(intValues = {1,2},message = "用户性别只能为1或2")
    private Integer gender;
}
