package com.lvpb.utils;

import cn.hutool.crypto.digest.DigestUtil;
import com.lvpb.constant.UserConstant;

public class PasswordEncrypt {

    public static String passwordEncrypt(String password){
        return DigestUtil.md5Hex(UserConstant.PASSWORD_ENCRYPT_SLAT + password);
    }
}
