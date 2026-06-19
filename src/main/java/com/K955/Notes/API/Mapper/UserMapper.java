package com.K955.Notes.API.Mapper;

import com.K955.Notes.API.DTOs.User.UserProfileResponse;
import com.K955.Notes.API.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserProfileResponse toUserProfileResponse(User user);

}
