package com.flowops.auth.service;

import com.flowops.auth.dto.LoginRequest;
import com.flowops.auth.entity.UserEntity;
import com.flowops.auth.exception.InvalidCredentialsException;
import com.flowops.auth.repository.TenantRepository;
import com.flowops.auth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private TenantRepository tenantRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void shouldLoginSuccessfully() {

        LoginRequest request = LoginRequest.builder()
                .email("carlos@example.com")
                .password("password")
                .build();

        UserEntity user = UserEntity.builder()
                .id("1")
                .email("carlos@example.com")
                .passwordHash("hashed")
                .tenantId("tenant1")
                .role("ADMIN")
                .build();

        when(userRepository.findByEmail("carlos@example.com"))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches("password", "hashed"))
                .thenReturn(true);

        when(jwtService.generateToken(user))
                .thenReturn("mocked-jwt");

        when(jwtService.getExpirationTime())
                .thenReturn(3600L);

        var response = authService.login(request);

        assertNotNull(response);
        assertEquals("mocked-jwt", response.getAccessToken());
        assertEquals("ADMIN", response.getRole());
        assertEquals(3600L, response.getExpiresIn());
    }

    @Test
    void shouldThrowExceptionForInvalidPassword() {

        LoginRequest request = LoginRequest.builder()
                .email("carlos@example.com")
                .password("wrong")
                .build();

        UserEntity user = UserEntity.builder()
                .email("carlos@example.com")
                .passwordHash("hashed")
                .build();

        when(userRepository.findByEmail("carlos@example.com"))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches("wrong", "hashed"))
                .thenReturn(false);

        assertThrows(
                InvalidCredentialsException.class,
                () -> authService.login(request)
        );
    }
}