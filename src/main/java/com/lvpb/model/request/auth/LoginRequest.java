package com.lvpb.model.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Data
@Schema(name = "登录参数")
public class LoginRequest implements Serializable {

    @Schema(description = "用户名",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "用户名不能为空")
    @Length(min = 4,max = 15,message = "用户名长度不能小于4位大于15位")
    private String username;

    @Schema(description = "密码",requiredMode = Schema.RequiredMode.REQUIRED)
    @Length(min = 8,max = 20,message = "密码长度不能小于8位大于20位")
    @NotBlank(message = "密码不能为空")
    private String password;
}
