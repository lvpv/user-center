package com.lvpb.exception;

import com.lvpb.common.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ExceptionCode implements ResultCode {

    MISS_REQUEST_PARAMS(40001, "缺失请求参数"),
    REQUEST_PARAMS_IS_EMPTY(40002, "请求参数为空"),
    REQUEST_PARAMS_ERROR(40002, "请求参数错误"),
    REQUEST_DATA_NOT_EXIST(40003,"请求数据不存在"),
    REQUEST_DATA_REPEAT(40003,"请求数据重复"),
    USER_NOT_LOGIN(500001,"用户未登录"),
    USER_NOT_PERMISSION(500002,"用户无权限")
    ;


    private final int code;

    private final String msg;

}
