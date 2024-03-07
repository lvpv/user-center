package com.lvpb.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvpb.mapper.UserMapper;
import com.lvpb.model.domain.User;
import com.lvpb.service.UserService;
import com.lvpb.utils.CheckRole;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户列表
     */
    @Override
    public List<User> searchUsers(String username, HttpSession session) {
        boolean admin = CheckRole.isAdmin(session);
        if (!admin){
            return new ArrayList<>(0);
        }
        return new LambdaQueryChainWrapper<>(baseMapper)
                .like(StrUtil.isNotBlank(username), User::getUsername, username)
                .list();
    }

    @Override
    public Boolean deleteUserById(Long id,HttpSession session) {
        boolean admin = CheckRole.isAdmin(session);
        if (!admin){
            return false;
        }
        int count = baseMapper.deleteById(id);
        return count > 0;
    }
}
