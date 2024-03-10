package com.lvpb.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvpb.constant.UserConstant;
import com.lvpb.convert.UserConvert;
import com.lvpb.enums.GenderEnum;
import com.lvpb.enums.RoleEnum;
import com.lvpb.enums.StatusEnum;
import com.lvpb.exception.BusinessException;
import com.lvpb.exception.ExceptionCode;
import com.lvpb.mapper.UserMapper;
import com.lvpb.model.domain.User;
import com.lvpb.model.request.user.AddUserRequest;
import com.lvpb.model.request.user.UpdateUserRequest;
import com.lvpb.service.UserService;
import com.lvpb.utils.CheckRole;
import com.lvpb.utils.PasswordEncrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Slf4j
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
           throw new BusinessException(ExceptionCode.USER_NOT_PERMISSION,"无删除权限");
        }
        int count = baseMapper.deleteById(id);
        return count > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(AddUserRequest addUserRequest) {
        if (Objects.isNull(addUserRequest)){
            log.info("用户信息为空");
            throw new BusinessException(ExceptionCode.REQUEST_PARAMS_IS_EMPTY, "用户信息为空");
        }
        if (StrUtil.isBlank(addUserRequest.getUsername())){
            log.info("用户名为空，username:{}", addUserRequest);
            throw new BusinessException(ExceptionCode.REQUEST_PARAMS_ERROR, "用户名为空");
        }
        String username = addUserRequest.getUsername();
        if (username.length() < 4 || username.length() > 15) {
            log.info("用户名格式错误，username:{}", username);
            throw new BusinessException(ExceptionCode.REQUEST_PARAMS_ERROR, "用户名长度错误");
        }
        Long count = new LambdaQueryChainWrapper<>(baseMapper).eq(User::getUsername, username).count();
        if (count > 0) {
            log.info("当前用户名已存在,username:{}", username);
            throw new BusinessException(ExceptionCode.REQUEST_DATA_REPEAT, "当前用户名已存在");
        }
        if (StrUtil.isBlank(addUserRequest.getNickname())){
            addUserRequest.setNickname(username);
        }
        if (StrUtil.isBlank(addUserRequest.getAvatar())){
            addUserRequest.setAvatar(UserConstant.DEFAULT_AVATAR);
        }
        if (Objects.isNull(addUserRequest.getRole())){
            addUserRequest.setRole(RoleEnum.DEFAULT.getCode());
        }
        if (Objects.isNull(addUserRequest.getGender())){
            addUserRequest.setGender(GenderEnum.UN_KNOW.getCode());
        }
        User user = UserConvert.INSTANCE.convertByAddUser(addUserRequest);
        user.setStatus(StatusEnum.NORMAL.getCode());
        String encryptPassword = PasswordEncrypt.passwordEncrypt(UserConstant.PASSWORD_ENCRYPT_SLAT + UserConstant.DEFAULT_PASSWORD);
        user.setPassword(encryptPassword);
        baseMapper.insert(user);
    }

    @Override
    public void updateUser(UpdateUserRequest updateUserRequest) {
        if (Objects.isNull(updateUserRequest)){
            log.info("用户信息为空");
            throw new BusinessException(ExceptionCode.REQUEST_PARAMS_IS_EMPTY, "用户信息为空");
        }
        Long id = updateUserRequest.getId();
        if (Objects.isNull(id)){
            log.info("用户信息为空:{}",updateUserRequest);
            throw new BusinessException(ExceptionCode.REQUEST_PARAMS_IS_EMPTY, "用户编号为空");
        }
        User queryUser = baseMapper.selectById(id);
        if (Objects.isNull(queryUser)){
            log.info("当前用户不存在:id:{}",id);
            throw new BusinessException(ExceptionCode.REQUEST_DATA_NOT_EXIST, "当前用户不存在");
        }
        User user = UserConvert.INSTANCE.convertByUpdateUser(updateUserRequest);
        baseMapper.updateById(user);
    }
}
