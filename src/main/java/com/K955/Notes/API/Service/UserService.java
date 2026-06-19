package com.K955.Notes.API.Service;

import com.K955.Notes.API.DTOs.User.UpdateUserProfileRequest;
import com.K955.Notes.API.DTOs.User.UserProfileResponse;
import jakarta.validation.Valid;

public interface UserService {
    UserProfileResponse userProfile(Long userId);

    UserProfileResponse updateUserProfile(Long userId, @Valid UpdateUserProfileRequest request);

    void deleteUser(Long userId);
}
