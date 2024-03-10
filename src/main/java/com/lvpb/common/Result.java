package com.lvpb.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    private String desc;


    private static <T> Result<T> getInstance(int code, String msg, T data, String desc) {
        Result<T> result = new Result<>();
        result.code = code;
        result.msg = msg;
        result.data = data;
        result.desc = desc;
        return result;
    }

    public static <T> Result<T> success(T data) {
        CommonCode success = CommonCode.SUCCESS;
        return Result.getInstance(success.getCode(), success.getMsg(), data, "");
    }

    public static Result<Void> success() {
        CommonCode success = CommonCode.SUCCESS;
        return Result.getInstance(success.getCode(), success.getMsg(), null, "");
    }

    public static Result<Void> fail(int code, String msg, String desc) {
        return Result.getInstance(code, msg, null, desc);
    }

    public static Result<Void> fail(ResultCode resultCode) {
        return Result.getInstance(resultCode.getCode(), resultCode.getMsg(), null, "");
    }

    public static Result<Void> fail(ResultCode resultCode, String desc) {
        return Result.getInstance(resultCode.getCode(), resultCode.getMsg(), null, desc);
    }
}
