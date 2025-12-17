package org.example.reminderapp.mapper;

import org.example.reminderapp.dto.*;
import org.example.reminderapp.entity.Reminder;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ReminderMapperDto {

    //DTO -> Entity
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Reminder toEntity(ReminderCreateDto dto);

    ReminderResponseDto toResponseDto(Reminder entity);

    List<ReminderResponseDto> toResponseList(List<Reminder> entities);

    //Update Entity of DTO
//    void updateEntity(@MappingTarget Reminder entity, ReminderUpdateDto dto);
}
