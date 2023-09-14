package com.blood_stock_server.business.mappers;

import com.blood_stock_server.business.repository.model.UserEntity;
import com.blood_stock_server.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity userToUserEntity(User user);

    User userEntityToUser(UserEntity userEntity);
}
