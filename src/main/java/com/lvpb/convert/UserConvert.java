package com.lvpb.convert;

import com.lvpb.model.domain.User;
import com.lvpb.model.request.user.AddUserRequest;
import com.lvpb.model.request.user.UpdateUserRequest;
import com.lvpb.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserResponse convertResponse(User user);

    List<UserResponse> convertResponses(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "createId", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateId", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    User convertByAddUser(AddUserRequest addUserRequest);


    @Mapping(target = "password", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "createId", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateId", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    User convertByUpdateUser(UpdateUserRequest updateUserRequest);


}
