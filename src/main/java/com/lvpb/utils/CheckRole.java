package com.lvpb.utils;

import com.lvpb.constant.UserConstant;
import com.lvpb.enums.RoleEnum;
import com.lvpb.exception.BusinessException;
import com.lvpb.exception.ExceptionCode;
import com.lvpb.model.domain.User;

import javax.servlet.http.HttpSession;
import java.util.Objects;

public class CheckRole {

    public static boolean isAdmin(HttpSession session){
        Object sessionUser = session.getAttribute(UserConstant.LOGIN_USER_STATE);
        if (Objects.isNull(sessionUser)){
            throw new BusinessException(ExceptionCode.USER_NOT_LOGIN);
        }
        User user = (User) sessionUser;
        return user.getRole() == RoleEnum.ADMIN.getCode();
    }
}
