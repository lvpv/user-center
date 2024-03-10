package com.lvpb.controller;


import com.lvpb.common.Result;
import com.lvpb.convert.UserConvert;
import com.lvpb.model.domain.User;
import com.lvpb.model.request.auth.LoginRequest;
import com.lvpb.model.request.auth.RegisterRequest;
import com.lvpb.model.response.UserResponse;
import com.lvpb.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Tag(name = "认证管理")
@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {


    @Resource
    private AuthService authService;


    @PostMapping("/login")
    @Operation(summary = "用户登陆")
    public Result<UserResponse> authLogin(@Valid @RequestBody LoginRequest loginRequest, HttpSession session) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        User user = authService.authLogin(username, password, session);
        UserResponse userResponse = UserConvert.INSTANCE.convertResponse(user);
        return Result.success(userResponse);
    }

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public Result<Long> authRegister(@Valid @RequestBody RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();
        String checkPassword = registerRequest.getCheckPassword();
        Long id = authService.authRegister(username, password, checkPassword);
        return Result.success(id);
    }

    @GetMapping("/info")
    @Operation(summary = "获取登录用户信息")
    public Result<UserResponse> getLoginInfo(HttpSession session){
        User user = authService.getLoginInfo(session);
        UserResponse userResponse = UserConvert.INSTANCE.convertResponse(user);
        return Result.success(userResponse);
    }

    @GetMapping("/logout")
    @Operation(summary = "用户注销")
    public Result<Void> authLogout(HttpSession session){
        authService.authLogout(session);
        return Result.success();
    }
}
