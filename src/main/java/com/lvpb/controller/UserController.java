package com.lvpb.controller;

import com.lvpb.convert.UserConvert;
import com.lvpb.model.domain.User;
import com.lvpb.model.response.UserResponse;
import com.lvpb.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "用户管理")
@Validated
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/search")
    @Parameter(name = "username",description = "用户名",in = ParameterIn.QUERY,ref = "String")
    public List<UserResponse> searchUsers(@RequestParam(required = false) String username, HttpSession session){
        List<User> users = userService.searchUsers(username,session);
        return UserConvert.INSTANCE.convertResponses(users);
    }

    @DeleteMapping("/delete/{id}")
    @Parameter(name = "id",description = "用户编号",in = ParameterIn.PATH,ref = "Long")
    public Boolean deleteUser(@PathVariable Long id,HttpSession session){
        return userService.deleteUserById(id,session);
    }
}
