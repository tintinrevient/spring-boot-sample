package com.hncy.demo.repository;

import com.hncy.demo.domain.User;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void givenUserInDBWhenFindByUsernameThenReturnListWithUser() {
        String username = "alex";

        User user = new User();
        user.setUsername(username);
        userRepository.save(user);

        List<User> userList = userRepository.findByUsername(username);

        assertThat(userList).isNotNull();
        assertThat(userList.size()).isEqualTo(1);
        assertThat(userList.get(0).getUsername()).isEqualTo(username);

    }

}
