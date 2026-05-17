package org.unread.controller;

import org.unread.dto.AuthRequest;
import org.unread.dto.RegisterUser;
import org.unread.repository.UserRepository;

import org.unread.service.JwtService;
import org.unread.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

 /*   @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUser request) {
        try {
            UserEntity user = userService.registerUser(request);
            String token = jwtService.generateToken(user.getMailId());

            return ResponseEntity.ok(new AuthResponse(
                    token,
                    new UserResponse(user.getId().toString(), user.getName(), user.getMailId(), user.getProvider().name())
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/


    @PostMapping("/api/auth/authenticate")
    String issueJwtToken(@RequestBody AuthRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getMailId(), authRequest.getPassword()));

        if (authentication.isAuthenticated())
            return jwtService.generateToken(authRequest.getMailId());

        return null;
    }


    @PostMapping("/api/auth/register")
    ResponseEntity<?> registerUser(@RequestBody RegisterUser registerUser) {

        userService.registerUser(registerUser);

        return ResponseEntity.ok().build();

    }
}
