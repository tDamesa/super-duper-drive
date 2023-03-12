package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialsTests {
    @LocalServerPort
    public int port;

    public static WebDriver driver;

    private final String USERNAME = "dan.joe@test.me";
    private final String PASSWORD = "test@123";
    private final String URL = "https://mypage.com";
    CredentialsPage credentialsPage;
    @BeforeAll
    public static void beforeAll(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll(){
        HomePage page = new HomePage(driver);
        page.logout();
        driver.quit();
    }

    @BeforeEach
    public void beforeEach(){
        signupAndLogin();
        credentialsPage = new CredentialsPage(driver);
        credentialsPage.navCredentialsPage();
    }

    private void signupAndLogin(){
        driver.get("http://localhost:" + port +"/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("Jone", "Doe", "jone.doe@test.com", "test@123");
        driver.get("http://localhost:" + port +"/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login( "jone.doe@test.com", "test@123");
    }

    @Test
    public void testCreateCredential() {
        credentialsPage.createNewCredential(USERNAME, PASSWORD, URL);
        ResultPage resultPage = new ResultPage(driver);
        resultPage.redirectToHome();
        credentialsPage.navCredentialsPage();
        assertEquals(URL, credentialsPage.getUrl());
        assertEquals(USERNAME, credentialsPage.getUsername());
        assertNotEquals(PASSWORD, credentialsPage.getPassword());
    }

    @Test
    public void  editCredential() {
        credentialsPage.createNewCredential(USERNAME, PASSWORD, URL);
        ResultPage resultPage = new ResultPage(driver);
        resultPage.redirectToHome();
        credentialsPage.navCredentialsPage();
        credentialsPage.editCredential("test@test.me");
        resultPage.redirectToHome();
        credentialsPage.navCredentialsPage();
        assertEquals(URL, credentialsPage.getUrl());
        assertEquals("test@test.me", credentialsPage.getUsername());
        assertNotEquals(PASSWORD, credentialsPage.getPassword());
    }

    @Test
    public void  deleteCredential() {
        credentialsPage.createNewCredential(USERNAME, PASSWORD, URL);
        ResultPage resultPage = new ResultPage(driver);
        resultPage.redirectToHome();
        credentialsPage.navCredentialsPage();
        credentialsPage.deleteCredential();
        driver.switchTo().alert().accept();
        resultPage.redirectToHome();
        credentialsPage.navCredentialsPage();
        assertTrue(driver.findElements(By.id("tblCredentialUrl")).isEmpty());
        assertTrue(driver.findElements(By.id("tblCredentialUsername")).isEmpty());
        assertTrue(driver.findElements(By.id("tblCredentialPassword")).isEmpty());
    }
}
