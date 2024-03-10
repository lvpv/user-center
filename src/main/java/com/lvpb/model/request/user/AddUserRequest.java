package com.lvpb.model.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(name = "添加用户参数")
@EqualsAndHashCode(callSuper = true)
public class AddUserRequest extends UserRequest{
}
