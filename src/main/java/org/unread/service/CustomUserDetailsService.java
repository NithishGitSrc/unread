package org.unread.service;

import org.unread.entity.UserEntity;
import org.unread.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mailId) throws UsernameNotFoundException {

        Optional<UserEntity> user = userRepository.findByMailId(mailId);

        if (user.isPresent()) {
            return User.builder()
                    .username(user.get().getMailId())
                    .password(user.get().getPassword())
                    .authorities(Collections.emptyList())
                    .build();
        }
        throw new UsernameNotFoundException("Mail Id not found");
    }
}
