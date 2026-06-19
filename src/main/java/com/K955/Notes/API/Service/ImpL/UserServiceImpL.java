package com.K955.Notes.API.Service.ImpL;

import com.K955.Notes.API.DTOs.User.UpdateUserProfileRequest;
import com.K955.Notes.API.DTOs.User.UserProfileResponse;
import com.K955.Notes.API.Entity.User;
import com.K955.Notes.API.Exceptions.ResourceNotFoundException;
import com.K955.Notes.API.Mapper.UserMapper;
import com.K955.Notes.API.Repository.UserRepository;
import com.K955.Notes.API.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserServiceImpL implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserProfileResponse userProfile(Long userId) {
        User user = getUser(userId);
        return userMapper.toUserProfileResponse(user);
    }

    @Override
    public UserProfileResponse updateUserProfile(Long userId, UpdateUserProfileRequest request) {
        User user = getUser(userId);

        if(request.name() != null) user.setName(request.name());
        if(request.password() != null) user.setPassword(request.password());

        User saved = userRepository.save(user);
        return userMapper.toUserProfileResponse(saved);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = getUser(userId);
        user.setDeletedAt(Instant.now());
        userRepository.save(user);
    }

    /// Util Methods

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(userId, "User"));
    }

}
