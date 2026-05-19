package org.unread.service;

import org.unread.entity.UserEntity;
import org.unread.repository.UserRepository;
import org.unread.security.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mailId) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(mailId)
                .orElseThrow(() -> new UsernameNotFoundException("Mail Id not found"));

        return new AuthenticatedUser(user.getId(), user.getEmail(), user.getPassword_hash());
    }
}
