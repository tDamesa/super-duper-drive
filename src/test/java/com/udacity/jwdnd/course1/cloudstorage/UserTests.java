package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTests {
    @LocalServerPort
    public int port;

    public static WebDriver driver;

    @BeforeAll
    public static void beforeAll(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll(){
        driver.quit();
    }

    @BeforeEach
    public void beforeEach(){

    }

    @Test
    public void testPageAccess(){
        driver.get("http://localhost:" + port +"/home");
        assertEquals("Login", driver.getTitle());


        driver.get("http://localhost:" + port +"/signup");
        assertEquals("Sign Up", driver.getTitle());

        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("Jone", "Doe", "jone.doe@test.com", "test@123");
        assertEquals("Login", driver.getTitle());

        driver.get("http://localhost:" + port +"/login");
        assertEquals("Login", driver.getTitle());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login( "jone.doe@test.com", "test@123");
        assertEquals("Home", driver.getTitle());

        HomePage homePage = new HomePage(driver);
        homePage.logout();
        assertEquals("Login", driver.getTitle());

    }

}
