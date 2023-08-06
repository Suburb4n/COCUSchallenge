package org.example.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class JwtAuthFilterTest {

    private JwtAuthFilter jwtAuthFilter;
    private UserDao mockUserDao;
    private JwtUtils mockJwtUtils;

    @BeforeEach
    public void setUp() {
        mockUserDao = mock(UserDao.class);
        mockJwtUtils = mock(JwtUtils.class);
        jwtAuthFilter = new JwtAuthFilter(mockUserDao, mockJwtUtils);
    }

    @Test
    public void testDoFilterInternal_validToken_shouldAuthenticateUser() throws ServletException, IOException {
        //Arrange
        String validJwtToken = "valid_jwt_token";
        String userEmail = "dluis1651@gmail.com";
        UserDetails userDetails = new User(userEmail, "password", Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_USER")));

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        FilterChain mockFilterChain = mock(FilterChain.class);

        when(mockRequest.getHeader("Authorization")).thenReturn("Bearer " + validJwtToken);
        when(mockJwtUtils.extractUsername(validJwtToken)).thenReturn(userEmail);
        when(mockUserDao.findUserByEmail(userEmail)).thenReturn(userDetails);
        when(mockJwtUtils.isTokenValid(validJwtToken, userDetails)).thenReturn(true);
        //Act
        jwtAuthFilter.doFilterInternal(mockRequest, mockResponse, mockFilterChain);
        //Assert
        verify(mockRequest).getHeader("Authorization");
        verify(mockJwtUtils).extractUsername(validJwtToken);
        verify(mockUserDao).findUserByEmail(userEmail);
        verify(mockJwtUtils).isTokenValid(validJwtToken, userDetails);
        verify(mockFilterChain).doFilter(mockRequest, mockResponse);
    }

    @Test
    public void testDoFilterInternal_invalidToken_shouldNotAuthenticateUserAndCallFilterChain() throws Exception {
        //Arrange
        String invalidJwtToken = "invalid_jwt_token";

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        FilterChain mockFilterChain = mock(FilterChain.class);

        when(mockRequest.getHeader("Authorization")).thenReturn("Bearer " + invalidJwtToken);
        when(mockJwtUtils.extractUsername(invalidJwtToken)).thenReturn(null);
        //Act
        jwtAuthFilter.doFilterInternal(mockRequest, mockResponse, mockFilterChain);
        //Assert
        verify(mockRequest).getHeader("Authorization");
        verify(mockJwtUtils).extractUsername(invalidJwtToken);
        verify(mockUserDao, never()).findUserByEmail(any());
        verify(mockJwtUtils, never()).isTokenValid(any(), any());
        verify(mockRequest, never()).setAttribute(eq("userDetails"), any());
        verify(mockFilterChain).doFilter(mockRequest, mockResponse);
    }

    @Test
    public void testDoFilterInternal_userNotFound_shouldNotAuthenticateUserAndCallFilterChain() throws Exception {
        //Arrange
        String validJwtToken = "valid_jwt_token";
        String userEmail = "nonexistinguser@example.com";

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        FilterChain mockFilterChain = mock(FilterChain.class);

        when(mockRequest.getHeader("Authorization")).thenReturn("Bearer " + validJwtToken);
        when(mockJwtUtils.extractUsername(validJwtToken)).thenReturn(userEmail);
        when(mockUserDao.findUserByEmail(userEmail)).thenReturn(null);
        //Act
        jwtAuthFilter.doFilterInternal(mockRequest, mockResponse, mockFilterChain);
        //Assert
        verify(mockRequest).getHeader("Authorization");
        verify(mockJwtUtils).extractUsername(validJwtToken);
        verify(mockUserDao).findUserByEmail(userEmail);
        verify(mockFilterChain).doFilter(mockRequest, mockResponse);
    }

    @Test
    public void testDoFilterInternal_shouldNotAuthenticateUserAndCallFilterChain() throws Exception {
        //Arrange
        String validJwtToken = "valid_jwt_token";
        String userEmail = "dluis1651@gmail.com";
        UserDetails userDetails = new User(userEmail, "password", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        FilterChain mockFilterChain = mock(FilterChain.class);

        when(mockRequest.getHeader("Authorization")).thenReturn("Bearer " + validJwtToken);
        when(mockJwtUtils.extractUsername(validJwtToken)).thenReturn(userEmail);
        when(mockUserDao.findUserByEmail(userEmail)).thenReturn(userDetails);
        when(mockJwtUtils.isTokenValid(validJwtToken, userDetails)).thenReturn(false);
        //Act
        jwtAuthFilter.doFilterInternal(mockRequest, mockResponse, mockFilterChain);
        //Assert
        verify(mockRequest).getHeader("Authorization");
        verify(mockJwtUtils).extractUsername(validJwtToken);
    }
}