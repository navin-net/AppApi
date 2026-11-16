package org.example.appapi.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.appapi.dto.request.SignInRequest;
import org.example.appapi.dto.request.SignUpRequest;
import org.example.appapi.dto.response.BaseResponse;
import org.example.appapi.dto.response.SignInResponse;
import org.example.appapi.exceptions.ErrorCode;
import org.example.appapi.exceptions.WebException;
import org.example.appapi.model.Role;
import org.example.appapi.model.User;
import org.example.appapi.repositories.RoleRepository;
import org.example.appapi.repositories.UserRepository;
import org.example.appapi.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> authenticateUser(
            @Valid @RequestBody SignInRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SignInResponse response = SignInResponse.builder()
                .accessToken(jwtUtils.generateToken(userDetails.getUsername()))
                .tokenType("Bearer")
                .username(userDetails.getUsername())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<Void>> registerUser(@Valid @RequestBody SignUpRequest request) {
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new WebException(ErrorCode.NOT_FOUND, "role not found"));

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new WebException(ErrorCode.DUPLICATE_DATA, "username already exists");
        }

        User newUser = User.builder()
                .role(role)
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(encoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .phone(request.getPhone())
                .active(true)
                .build();

        userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.success("User registered successfully!", null));
    }
}
