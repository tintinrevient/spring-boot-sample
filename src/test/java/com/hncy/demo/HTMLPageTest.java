package com.hncy.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HTMLPageTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebDriverManager.chromedriver().version("72").setup();
    }

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void openUserURL() {
        String baseUrl = "http://localhost:" + port + "/user/1";
        driver.navigate().to(baseUrl);

        WebElement pre = driver.findElement(By.tagName("pre"));
        assertThat(pre.getText(), containsString("\"id\":1,\"username\":\"user\""));
    }

}
