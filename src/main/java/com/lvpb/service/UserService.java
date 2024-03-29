package com.lvpb.service;

import com.lvpb.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lvpb.model.request.user.AddUserRequest;
import com.lvpb.model.request.user.UpdateUserRequest;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService extends IService<User>{


    List<User> searchUsers(String username, HttpSession session);

    Boolean deleteUserById(Long id,HttpSession session);

    void addUser(AddUserRequest addUserRequest);

    void updateUser(UpdateUserRequest updateUserRequest);
}
