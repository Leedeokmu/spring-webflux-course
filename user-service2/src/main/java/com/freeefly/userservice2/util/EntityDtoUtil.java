package com.freeefly.userservice2.util;

import com.freeefly.userservice2.dto.TransactionRequestDto;
import com.freeefly.userservice2.dto.TransactionResponseDto;
import com.freeefly.userservice2.dto.TransactionStatus;
import com.freeefly.userservice2.dto.UserDto;
import com.freeefly.userservice2.entity.User;
import com.freeefly.userservice2.entity.UserTransaction;
import java.time.LocalDateTime;

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

    public static UserTransaction toEntity(TransactionRequestDto requestDto) {
        UserTransaction ut = new UserTransaction();
        ut.setUserId(requestDto.getUserId());
        ut.setAmount(requestDto.getAmount());
        ut.setTransactionDate(LocalDateTime.now());
        return ut;
    }

    public static TransactionResponseDto toDto(TransactionRequestDto requestDto,
        TransactionStatus status) {
        TransactionResponseDto dto = new TransactionResponseDto();
        dto.setAmount(requestDto.getAmount());
        dto.setUserId(requestDto.getAmount());
        dto.setStatus(status);
        return dto;
    }


}
