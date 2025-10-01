package com.sii.java.codingtask.user;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    private static final Double TEST_USER_BALANCE = 10D;
    private static final String TEST_LAST_NAME = "testLastName";
    private static final String TEST_FIRST_NAME = "testFirstName";
    private UserRepository userRepository;
    private UserController userController;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userController = new UserController(userRepository);
    }

    @Test
    public void shouldAddNewUserAndReturnCreatedUserId() {
        NewUser newUser = new NewUser(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USER_BALANCE);
        UserEntity newUserEntity = UserEntity.builder()
                .firstName(TEST_FIRST_NAME)
                .lastName(TEST_LAST_NAME)
                .balance(TEST_USER_BALANCE)
                .build();
        UserEntity user = new UserEntity(1L, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USER_BALANCE);
        when(userRepository.save(newUserEntity)).thenReturn(user);

        long newUserId = userController.createUser(newUser);

        assertThat(newUserId).isEqualTo(1L);
    }

    @Test
    public void shouldReturnTwoAddedUsers() {
        UserEntity user = new UserEntity(1L, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USER_BALANCE);
        when(userRepository.findAll()).thenReturn(List.of(user));

        Collection<UserEntity> users = userController.getUsers();

        UserEntity expectedUser = new UserEntity(1L, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USER_BALANCE);
        Assertions.assertThat(users).containsExactly(expectedUser);
    }

    @Test
    public void shouldReturnNullBecauseUserIsDeleted() {

        userController.deleteUser(1L);

        verify(userRepository).deleteById(1L);
    }

}
