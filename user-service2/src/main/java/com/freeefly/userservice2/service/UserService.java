package com.freeefly.userservice2.service;

import com.freeefly.userservice2.dto.UserDto;
import com.freeefly.userservice2.repository.UserRepository;
import com.freeefly.userservice2.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Flux<UserDto> all() {
        return userRepository.findAll()
            .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> getUserById(final int userId) {
        return userRepository.findById(userId)
            .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> createUser(Mono<UserDto> userDtoMono) {
        return userDtoMono
            .map(EntityDtoUtil::toEntity)
            .flatMap(userRepository::save)
            .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> updateUser(int id, Mono<UserDto> userDtoMono) {
        return userRepository.findById(id)
            .flatMap(u -> userDtoMono.map(EntityDtoUtil::toEntity)
                .doOnNext(e -> e.setId(id))
            ).flatMap(userRepository::save)
            .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> deleteUser(int id) {
        return userRepository.deleteById(id);
    }


}
