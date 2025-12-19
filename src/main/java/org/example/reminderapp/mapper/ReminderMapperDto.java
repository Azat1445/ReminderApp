package org.example.reminderapp.mapper;

import org.example.reminderapp.dto.*;
import org.example.reminderapp.entity.Reminder;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ReminderMapperDto {

    //Entity -> DTO
    ReminderResponseDto toDto(Reminder entity);

    //DTO -> Entity
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Reminder toEntity(ReminderCreateDto dto);

    //List mapping
    List<ReminderResponseDto> toResponseList(List<Reminder> entities);

    //Update Entity of DTO
    void updateEntity(ReminderUpdateDto dto,@MappingTarget Reminder entity);
}
