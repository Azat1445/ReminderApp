package org.example.reminderapp.service;


import org.example.reminderapp.entity.User;
import org.example.reminderapp.mapper.UserMapperDto;
import org.example.reminderapp.mapper.UserMapperDtoImpl;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.reminderapp.dto.UserProfileResponseDto;
import org.example.reminderapp.repository.ReminderRepository;
import org.example.reminderapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ReminderRepository reminderRepository;
    private final UserMapperDto userMapperDto;
    private final UserMapperDto userMapperDto;

    @Transactional
    public List<UserProfileResponseDto> findAll() {
        List<User> users = userRepository.findAll();
        userMapperDto.toDtoList(users);
        return List<UserProfileResponseDto>

    }

    @Transactional
    public Optional<UserProfileResponseDto> findById() {
        return null;
    }

    @Transactional
    public UserProfileResponseDto create() {
        return
    }

    @Transactional
    public Optional<UserProfileResponseDto> update(Long id) {

    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
