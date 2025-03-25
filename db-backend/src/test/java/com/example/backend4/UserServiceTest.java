package com.example.backend4;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.backend4.model.auth.Role;
import com.example.backend4.model.auth.User;
import com.example.backend4.repository.UserRepository;
import com.example.backend4.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = new User("testUser", "testPassword", Role.ELF);
    }

    @Test
    void testLoadUserByUsername_UserFound() {
        when(userRepository.findByUsername("testUser")).thenReturn(mockUser);

        UserDetails userDetails = userService.loadUserByUsername("testUser");

        assertNotNull(userDetails);
        assertEquals("testUser", userDetails.getUsername());
        assertEquals("testPassword", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ELF")));
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByUsername("nonExistentUser")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("nonExistentUser"));
    }

    @Test
    void testLoadUserByUsername_UserWithDifferentRole() {
        User adminUser = new User("adminUser", "adminPassword", Role.SANTA);

        when(userRepository.findByUsername("adminUser")).thenReturn(adminUser);
        UserDetails userDetails = userService.loadUserByUsername("adminUser");
        assertNotNull(userDetails);
        assertEquals("adminUser", userDetails.getUsername());
        assertEquals("adminPassword", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("SANTA")));
    }
}

