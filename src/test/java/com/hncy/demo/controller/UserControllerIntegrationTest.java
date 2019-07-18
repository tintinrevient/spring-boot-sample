package com.hncy.demo.controller;

import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.*;
import com.hncy.demo.repository.UserRepository;
import com.hncy.demo.domain.User;
import java.util.Arrays;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {

        User alex = new User();
        alex.setUsername("alex");
        alex.setRoles("READER");

        User alice = new User();
        alice.setUsername("alice");
        alice.setRoles("READER,WRITER");

        User admin = new User();
        admin.setUsername("admin");
        admin.setRoles("READER,WRITER,ADMIN");

        Page<User> page_0 = new PageImpl<User>(Arrays.asList(admin));
        Page<User> page_1 = new PageImpl<User>(Arrays.asList(alex));
        Page<User> page_2 = new PageImpl<User>(Arrays.asList(alice));

        Mockito.when(userRepository.findAll(PageRequest.of(0, 1))).thenReturn(page_0);
        Mockito.when(userRepository.findAll(PageRequest.of(1, 1))).thenReturn(page_1);
        Mockito.when(userRepository.findAll(PageRequest.of(2, 1))).thenReturn(page_2);

    }

    @Test
    public void findPagedAll() throws Exception {
        mockMvc.perform(get("/user/findPagedAll?page=0&size=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json; charset=UTF-8"))
                .andExpect(jsonPath("$.content[0].username", is("admin")));
    }

    @Test
    public void save() throws Exception {
        mockMvc.perform(post("/user")
                .contentType(APPLICATION_JSON_VALUE)
                .content("{\"username\":\"test\",\"password\":\"password\",\"fullname\":\"test\",\"roles\":\"TESTER\"}"))
                .andExpect(status().isOk());
    }

}
