package com.hncy.demo.service;

import com.hncy.demo.domain.User;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void whenAddRoleToAllThenReturnListWithUser() {

        List<User> userList = userService.addRoleToAllUsers("TESTER");

        assertThat(userList).isNotNull();
        assertThat(userList.size()).isEqualTo(3);
        assertThat(userList.get(0).getRoles()).isEqualTo("TESTER,USER");
        assertThat(userList.get(1).getRoles()).isEqualTo("TESTER,WRITER,ADMIN,READER,USER");
        assertThat(userList.get(2).getRoles()).isEqualTo("TESTER,ADMIN,READER,USER");

    }

}
