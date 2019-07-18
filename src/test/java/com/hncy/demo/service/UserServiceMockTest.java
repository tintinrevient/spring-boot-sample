package com.hncy.demo.service;

import com.hncy.demo.domain.User;
import com.hncy.demo.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceMockTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Before
    public void setUp() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setRoles("ADMIN");

        List<User> userArrayList = new ArrayList<>();
        userArrayList.add(admin);

        Mockito.when(userRepository.findAll()).thenReturn(userArrayList);
    }

    @Test
    public void testFindAll() {

        List<User> userList = userService.findAll();
        assertThat(userList).isNotNull();
        assertThat(userList.size()).isEqualTo(1);
        assertThat(userList.get(0).getUsername()).isEqualTo("admin");
    }

}
