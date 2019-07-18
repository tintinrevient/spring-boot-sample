package com.hncy.demo.controller;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.model.RequestResponsePact;
import com.hncy.demo.domain.Review;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerTests {

    @Autowired
    private RestTemplate restTemplate;

    @Rule
    public PactProviderRuleMk2 reviewService = new PactProviderRuleMk2
            ("review_service", "localhost", 6061, this);

    @Pact(consumer="demo_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) throws IOException {
        return builder
                .given("review service")
                .uponReceiving("a request for the review service")
                .path("/review/1")
                .method("GET")
                .willRespondWith() .status(200)
                .body("{\"id\":1,\"subject\":\"cup review\",\"comment\":\"a good cup\",\"user\":1,\"product\":1}", MediaType.APPLICATION_JSON_VALUE)
                .toPact();
    }

    @Test
    @PactVerification("review_service")
    public void aggregate() {

        Review expectedReview = new Review();
        expectedReview.setId(1l);
        expectedReview.setSubject("cup review");
        expectedReview.setComment("a good cup");
        expectedReview.setUser(1l);
        expectedReview.setProduct(1l);

        assertThat(restTemplate.getForEntity("http://localhost:6061/review/1", Review.class).getBody()).isEqualTo(expectedReview);
    }
}
