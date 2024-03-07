package com.lvpb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    DEFAULT(0),
    ADMIN(1),
    ;
    private final int code;
}
