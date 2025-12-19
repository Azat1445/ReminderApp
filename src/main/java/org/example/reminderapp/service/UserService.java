package org.example.reminderapp.service;


import org.example.reminderapp.dto.UserCreateDto;
import org.example.reminderapp.dto.UserFilterDto;
import org.example.reminderapp.entity.User;
import org.example.reminderapp.exception.ResourceNotFoundException;
import org.example.reminderapp.mapper.UserMapperDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.reminderapp.dto.UserProfileResponseDto;
import org.example.reminderapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapperDto userMapperDto;

    public Page<UserProfileResponseDto> findAll(Pageable pageable) {
        //пока без фильтров для пагинации

        return userRepository.findAll(pageable)
                .map(userMapperDto::toDto);
    }

    public UserProfileResponseDto findById(Long id) {
        return userRepository.findById(id)
                .map(userMapperDto::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Transactional
    public UserProfileResponseDto create(UserCreateDto userDto) {
        User entity = userMapperDto.toEntity(userDto);
        User savedEntity = userRepository.save(entity);
        return userMapperDto.toDto(savedEntity);
    }

    @Transactional
    public UserProfileResponseDto update(Long id, UserCreateDto userDto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userMapperDto.updateEntity(userDto, user);

        User savedEntity = userRepository.save(user);

        return userMapperDto.toDto(savedEntity);
    }

    @Transactional
    public void delete(Long id) throws ResourceNotFoundException {

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
