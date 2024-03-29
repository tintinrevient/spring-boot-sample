package com.hncy.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.hncy.demo.domain.User;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import java.util.Collection;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testRestTemplate() throws Exception {

        ResponseEntity<PagedResources<User>> responseEntity = testRestTemplate.exchange(
                "http://localhost:" + port + "/user/findPagedAll?page=0&size=10", HttpMethod.GET, null, new ParameterizedTypeReference<PagedResources<User>>() {});

        Collection<User> users = responseEntity.getBody().getContent();

        assertThat(users.size()).isEqualTo(0);
    }
}
