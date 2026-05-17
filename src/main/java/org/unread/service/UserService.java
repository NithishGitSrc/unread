package org.unread.service;


import org.unread.dto.RegisterUser;
import org.unread.entity.UserEntity;
import org.unread.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity registerUser(RegisterUser request) {
        if (userRepository.existsByMailId(request.getMailId())) {
            throw new RuntimeException("Email already exists");
        }

        UserEntity user = UserEntity.builder()
                .name(request.getName())
                .mailId(request.getMailId())
                .password(passwordEncoder.encode(request.getPassword()))
                .provider(UserEntity.AuthProvider.LOCAL)
                .build();

        return userRepository.save(user);
    }
}
