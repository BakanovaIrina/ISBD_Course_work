package com.example.backend4;

import com.example.backend4.model.auth.Role;
import com.example.backend4.model.auth.User;
import com.example.backend4.repository.UserRepository;
import com.example.backend4.services.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Test
    void testParseAuthHeader() {
        String authHeader = "santa:12345";
        User user = authService.parseAuthHeader(authHeader);

        assertEquals("santa", user.getUsername());
        assertEquals("12345", user.getPassword());
    }

    @Test
    void testAuthenticateUser_Success() {
        User user = new User("santa", "12345");

        String encodedPassword = "$2a$04$abcdefghijklmnopqrstuvwx";
        User storedUser = new User("santa", encodedPassword);

        when(userRepository.findByUsername("santa")).thenReturn(storedUser);
        when(passwordEncoder.matches("12345", encodedPassword)).thenReturn(true);

        assertTrue(authService.authenticateUser(user));

        verify(userRepository, times(1)).findByUsername("santa");
        verify(passwordEncoder, times(1)).matches("12345", encodedPassword);
    }

    @Test
    void testAuthenticateUser_UserNotFound() {
        User user = new User("santa", "12345");

        when(userRepository.findByUsername("santa")).thenReturn(null);
        assertFalse(authService.authenticateUser(user));
    }

    @Test
    void testAuthenticateUser_InvalidPassword() {
        User user = new User("santa", "wrongPassword");

        String encodedPassword = "$2a$04$abcdefghijklmnopqrstuvwx";
        User storedUser = new User("santa", encodedPassword);

        when(userRepository.findByUsername("santa")).thenReturn(storedUser);
        when(passwordEncoder.matches("wrongPassword", encodedPassword)).thenReturn(false);

        assertFalse(authService.authenticateUser(user));

        verify(userRepository, times(1)).findByUsername("santa");
        verify(passwordEncoder, times(1)).matches("wrongPassword", encodedPassword);
    }

    @Test
    void testRegisterUser_Success() {
        User newUser = new User("newUser", "password123", Role.ELF);
        when(userRepository.findByUsername("newUser")).thenReturn(null);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");

        assertTrue(authService.registerUser(newUser));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_UserAlreadyExists() {
        User existingUser = new User("existingUser", "password123", Role.ELF);
        when(userRepository.findByUsername("existingUser")).thenReturn(existingUser);

        assertFalse(authService.registerUser(existingUser));
        verify(userRepository, never()).save(any(User.class));
    }
}
