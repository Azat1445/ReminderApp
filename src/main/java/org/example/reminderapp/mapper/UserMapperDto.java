package org.example.reminderapp.mapper;


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
    UserProfileResponseDto toDto(User entity);

    //List mapping
    List<UserProfileResponseDto> toDtoList(List<User> entities);

}
