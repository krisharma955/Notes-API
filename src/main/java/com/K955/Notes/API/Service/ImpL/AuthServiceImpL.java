package com.K955.Notes.API.Service.ImpL;

import com.K955.Notes.API.DTOs.Auth.AuthResponse;
import com.K955.Notes.API.DTOs.Auth.LoginRequest;
import com.K955.Notes.API.DTOs.Auth.SignupRequest;
import com.K955.Notes.API.Entity.User;
import com.K955.Notes.API.Exceptions.BadRequestException;
import com.K955.Notes.API.Exceptions.ResourceNotFoundException;
import com.K955.Notes.API.Mapper.UserMapper;
import com.K955.Notes.API.Repository.UserRepository;
import com.K955.Notes.API.Security.JwtAuthUtil;
import com.K955.Notes.API.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpL implements AuthService {

    private final UserRepository userRepository;
    private final JwtAuthUtil jwtAuthUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse signup(SignupRequest request) {
        Boolean check = userRepository.existsByUsername(request.username());
        if(check) throw new BadRequestException("User with username: " +request.username()+ " already exists.");

        User user = User.builder()
                .name(request.name())
                .username(request.username())
                .password(request.password())
                .build();
        user.setPassword(passwordEncoder.encode(request.password()));
        User saved = userRepository.save(user);

        String accessToken = jwtAuthUtil.generateAccessToken(saved);

        return new AuthResponse(accessToken, userMapper.toUserProfileResponse(saved));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
        } catch (Exception e) {
            throw new BadRequestException("Invalid Username or Password");
        }

        User user = userRepository.findUserByUsername(request.username())
                .orElseThrow(() -> new ResourceNotFoundException(request.username(), "User"));

        String accessToken = jwtAuthUtil.generateAccessToken(user);

        return new AuthResponse(accessToken, userMapper.toUserProfileResponse(user));
    }
}
