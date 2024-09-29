package com.amagana.patient_mvc.utils;

import com.amagana.patient_mvc.dto.UsersDtoRequest;
import com.amagana.patient_mvc.dto.UsersDtoResponse;
import com.amagana.patient_mvc.entities.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsersMapper {
    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);
    Users usersDtoRequestToUsers(UsersDtoRequest usersDtoRequest);
    UsersDtoResponse usersToUsersDtoResponse(Users users);
}
