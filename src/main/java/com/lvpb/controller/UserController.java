package com.lvpb.controller;

import com.lvpb.common.Result;
import com.lvpb.convert.UserConvert;
import com.lvpb.model.domain.User;
import com.lvpb.model.request.user.AddUserRequest;
import com.lvpb.model.request.user.UpdateUserRequest;
import com.lvpb.model.response.UserResponse;
import com.lvpb.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "用户管理")
@Validated
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/search")
    @Operation(summary = "查询用户列表")
    @Parameter(name = "username",description = "用户名",in = ParameterIn.QUERY,ref = "String")
    public Result<List<UserResponse>> searchUsers(@RequestParam(required = false) String username, HttpSession session){
        List<User> users = userService.searchUsers(username,session);
        List<UserResponse> userResponses = UserConvert.INSTANCE.convertResponses(users);
        return Result.success(userResponses);
    }

    @PostMapping("/add")
    @Operation(summary = "添加用户")
    public Result<Void> addUser(@Valid @RequestBody AddUserRequest addUserRequest){
        userService.addUser(addUserRequest);
        return Result.success();
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户")
    public Result<Void> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest){
        userService.updateUser(updateUserRequest);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除用户")
    @Parameter(name = "id",description = "用户编号",in = ParameterIn.PATH,ref = "Long")
    public Result<Boolean> deleteUser(@PathVariable Long id,HttpSession session){
        Boolean result = userService.deleteUserById(id, session);
        return Result.success(result);
    }
}
