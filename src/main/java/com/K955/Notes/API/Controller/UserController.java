package com.K955.Notes.API.Controller;

import com.K955.Notes.API.DTOs.User.UpdateUserProfileRequest;
import com.K955.Notes.API.DTOs.User.UserProfileResponse;
import com.K955.Notes.API.Security.JwtAuthUtil;
import com.K955.Notes.API.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final JwtAuthUtil jwtAuthUtil;

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> userProfile() {
        Long userId = jwtAuthUtil.getCurrentUserId();
        return ResponseEntity.ok(userService.userProfile(userId));
    }

    @PatchMapping
    public ResponseEntity<UserProfileResponse> updateUserProfile(@Valid @RequestBody UpdateUserProfileRequest request) {
        Long userId = jwtAuthUtil.getCurrentUserId();
        return ResponseEntity.ok(userService.updateUserProfile(userId, request));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser() {
        Long userId = jwtAuthUtil.getCurrentUserId();
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
