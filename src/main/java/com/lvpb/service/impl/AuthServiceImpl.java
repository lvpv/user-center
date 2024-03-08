package com.lvpb.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lvpb.constant.CommonConstant;
import com.lvpb.constant.UserConstant;
import com.lvpb.model.domain.User;
import com.lvpb.service.AuthService;
import com.lvpb.service.UserService;
import com.lvpb.utils.PasswordEncrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private UserService userService;

    /**
     * 登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @param session  session
     */
    @Override
    public User authLogin(String username, String password, HttpSession session) {
        if (StrUtil.isAllBlank(username, password)) {
            return null;
        }
        if (username.length() < 4 || username.length() > 15) {
            return null;
        }
        if (password.length() < 8 || password.length() > 20) {
            return null;
        }
        // 检测密码特殊字符
        // 加密
        String encryptPassword = PasswordEncrypt.passwordEncrypt(password);
        User user = userService.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username)
                .eq(User::getPassword, encryptPassword)
                .last(CommonConstant.QUERY_ONE_SQL));
        if (Objects.isNull(user)) {
            log.info("用户名或密码错误,username:{}", username);
            return null;
        }
        // 脱敏
        session.setAttribute(UserConstant.LOGIN_USER_STATE, user);
        // 保存登录态
        return user;
    }

    /**
     * 注册接口
     *
     * @param username      用户名
     * @param password      密码
     * @param checkPassword 确认密码
     * @return 用户编号
     */
    @Override
    public Long authRegister(String username, String password, String checkPassword) {
        if (StrUtil.isAllBlank(username, password, checkPassword)) {
            return -1L;
        }
        if (username.length() < 4 || username.length() > 15) {
            return -1L;
        }
        if (password.length() < 8 || password.length() > 20 || checkPassword.length() < 8 || checkPassword.length() > 20) {
            return -1L;
        }
        if (!password.equals(checkPassword)) {
            return -1L;
        }
        // 检测密码特殊字符
        long count = userService.count(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (count > 0) {
            return -1L;
        }
        String encryptPassword = PasswordEncrypt.passwordEncrypt(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(encryptPassword);
        user.setNickname(username);
        userService.save(user);
        return user.getId();
    }

    /**
     * 获取当前登录用户信息
     *
     * @param session session
     * @return 登录用户信息
     */
    @Override
    public User getLoginInfo(HttpSession session) {
        Object sessionUser = session.getAttribute(UserConstant.LOGIN_USER_STATE);
        return (User) sessionUser;
    }

    @Override
    public void authLogout(HttpSession session) {
        Object sessionUser = session.getAttribute(UserConstant.LOGIN_USER_STATE);
        if (Objects.nonNull(sessionUser)){
            session.removeAttribute(UserConstant.LOGIN_USER_STATE);
        }
    }
}
