package com.lvpb.model.request.user;


import com.lvpb.validation.EnumValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@Schema(name = "添加用户参数")
@EqualsAndHashCode(callSuper = true)
public class UpdateUserRequest extends UserRequest{

    /**
     * 用户编号
     */
    @Schema(description="用户昵称")
    @NotNull(message = "用户编号不能为空")
    private Long id;

    /**
     * 状态(0:正常,1:禁用)
     */
    @Schema(description="状态")
    @EnumValue(intValues = {0,1},message = "用户状态只能为0或1")
    private Integer status;
}
