package com.freeefly.userservice2.service;

import com.freeefly.userservice2.dto.TransactionRequestDto;
import com.freeefly.userservice2.dto.TransactionResponseDto;
import com.freeefly.userservice2.dto.TransactionStatus;
import com.freeefly.userservice2.repository.UserRepository;
import com.freeefly.userservice2.repository.UserTransactionRepository;
import com.freeefly.userservice2.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final UserRepository userRepository;
    private final UserTransactionRepository userTransactionRepository;

    @Transactional
    public Mono<TransactionResponseDto> createTransaction(TransactionRequestDto requestDto) {
        return userRepository.updateUserBalance(requestDto.getUserId(), requestDto.getAmount())
            .filter(Boolean::booleanValue)
            .map(b -> EntityDtoUtil.toEntity(requestDto))
            .flatMap(userTransactionRepository::save)
            .map(ut -> EntityDtoUtil.toDto(requestDto, TransactionStatus.APPROVED))
            .defaultIfEmpty(EntityDtoUtil.toDto(requestDto, TransactionStatus.DECLINED));
    }

}
