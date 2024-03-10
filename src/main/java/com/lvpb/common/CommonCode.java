package com.lvpb.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonCode implements ResultCode {

    SUCCESS(0, "操作成功"),

    SERVER_ERROR(500, "系统异常，请稍后再试！");

    private final int code;

    private final String msg;

}
