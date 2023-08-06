package org.example.config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class SecurityConfigTest {

    private SecurityConfig securityConfig;

    private JwtAuthFilter mockJwtAuthFilter;

    private UserDao mockUserDao;

    @BeforeEach
    public void setUp() {
        mockJwtAuthFilter = mock(JwtAuthFilter.class);
        mockUserDao = mock(UserDao.class);

        securityConfig = new SecurityConfig(mockJwtAuthFilter, mockUserDao);
    }


    @Test
    public void authenticationManagerConfiguration_isCreated() throws Exception {
        //Arrange
        AuthenticationConfiguration config = mock(AuthenticationConfiguration.class);
        when(config.getAuthenticationManager()).thenReturn(mock(AuthenticationManager.class));
        //Act
        AuthenticationManager authenticationManager = securityConfig.authenticationManager(config);
        boolean result = authenticationManager != null;
        //Assert
        assertNotNull(result) ;
    }

    @Test
    public void passwordEncoderConfiguration_isCreated() {
        //Arrange
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        //Act
        boolean result = passwordEncoder instanceof NoOpPasswordEncoder;
        //Assert
        assertNotNull(result);
    }

    @Test
    public void userDetailsServiceConfiguration_isCreated() {
        //Arrange
        UserDetailsService userDetailsService = securityConfig.userDetailsService();
        //Act
        boolean result =userDetailsService != null;
        //Assert
        assertNotNull(result);
    }
}
