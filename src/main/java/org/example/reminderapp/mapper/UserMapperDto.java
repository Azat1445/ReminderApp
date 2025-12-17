package org.example.reminderapp.mapper;


import org.example.reminderapp.dto.UserCreateDto;
import org.example.reminderapp.dto.UserProfileResponseDto;
import org.example.reminderapp.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {ReminderMapperDto.class})
public interface UserMapperDto {

    //Entity -> DTO
    @Mapping(source = "reminders", target = "reminders")
    @Mapping(source = "birthDate", target = "birthDate")
    UserProfileResponseDto toDto(User entity);

    //DTO -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "reminders", ignore = true)
    @Mapping(target = "birthDate", source = "birthDate")
    User toEntity(UserCreateDto dto);

    //List mapping
    List<UserProfileResponseDto> toDtoList(List<User> entities);

}
