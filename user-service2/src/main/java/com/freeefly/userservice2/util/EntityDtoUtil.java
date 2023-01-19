package com.freeefly.userservice2.util;

import com.freeefly.userservice2.dto.UserDto;
import com.freeefly.userservice2.entity.User;

public class EntityDtoUtil {

    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setBalance(user.getBalance());
        return dto;
    }

    public static User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setBalance(dto.getBalance());
        return user;
    }


}
