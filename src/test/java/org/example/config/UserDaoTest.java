package org.example.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        userDao = new UserDao();
    }

    @Test
    public void testFindUserByEmail_userFound() {
        //Arrange
        String email = "dluis1651@gmail.com";
        //Act
        UserDetails userDetails = userDao.findUserByEmail(email);
        //Assert
        assertEquals(email, userDetails.getUsername());
    }

    @Test
    public void testFindUserByEmail_nonExistingUser() {
        //Arrange
        String email = "nonexistinguser@example.com";
        String expected= "User not found";
        //Assert
         assertThrows(UsernameNotFoundException.class,
                () -> userDao.findUserByEmail(email),expected);

    }

    @Test
    public void testFindUserByEmail_nullEmail_shouldThrowIllegalArgumentException() {
        //Arrange
        String expected= "User not found";
        //Assert
        assertThrows(UsernameNotFoundException.class, () -> userDao.findUserByEmail(null),
                expected);
    }

}