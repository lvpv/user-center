package com.lvpb.service;

import com.lvpb.model.domain.User;

import javax.servlet.http.HttpSession;

public interface AuthService {

    User authLogin(String username, String password, HttpSession session);

    Long authRegister(String username,String password,String checkPassword);

    User getLoginInfo(HttpSession session);

    void authLogout(HttpSession session);

}
