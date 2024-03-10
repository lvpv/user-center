package com.lvpb.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    NORMAL(0, "正常"),
    DISABLE(1, "禁用");

    private final int code;

    private final String status;
}
