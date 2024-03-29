package com.lvpb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    DEFAULT(0,"普通用户"),
    ADMIN(1,"管理员");
    private final int code;

    private final String role;
}
