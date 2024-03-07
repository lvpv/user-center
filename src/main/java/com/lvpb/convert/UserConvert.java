package com.lvpb.convert;

import com.lvpb.model.domain.User;
import com.lvpb.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserResponse convertResponse(User user);

    List<UserResponse> convertResponses(List<User> users);
}
