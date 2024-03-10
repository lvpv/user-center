package com.lvpb.exception;


import com.lvpb.common.ResultCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {


    private final int code;
    private final String desc;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.desc = "";
    }

    public BusinessException(int code, String message, String desc) {
        super(message);
        this.code = code;
        this.desc = desc;
    }

    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
        this.desc = "";
    }

    public BusinessException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.desc = "";
    }

    public BusinessException(int code, String message, String desc, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.desc = desc;
    }


    public BusinessException(ResultCode resultCode,String desc) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
        this.desc = desc;
    }

    public BusinessException(ResultCode resultCode,Throwable throwable) {
        super(resultCode.getMsg(),throwable);
        this.code = resultCode.getCode();
        this.desc = "";
    }

    public BusinessException(ResultCode resultCode, String desc,Throwable throwable) {
        super(resultCode.getMsg(), throwable);
        this.code = resultCode.getCode();
        this.desc = desc;
    }


}
