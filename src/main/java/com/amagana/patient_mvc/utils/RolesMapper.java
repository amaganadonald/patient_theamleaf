package com.amagana.patient_mvc.utils;

import com.amagana.patient_mvc.dto.RolesDtoRequest;
import com.amagana.patient_mvc.dto.RolesDtoResponse;
import com.amagana.patient_mvc.entities.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RolesMapper {
    RolesMapper INSTANCE = Mappers.getMapper(RolesMapper.class);

    Roles rolesDtoRequestToRoles(RolesDtoRequest rolesDtoRequest);
    RolesDtoResponse rolesToRolesDtoResponse(Roles roles);
}
