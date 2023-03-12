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
public class NoteTests {
    @LocalServerPort
    public int port;

    public static WebDriver driver;

    private final String TITLE = "Note title";
    private final String DESCRIPTION = "Note description";
    NotePage notePage;
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
        notePage = new NotePage(driver);
        notePage.navNotePage();
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
        notePage.createNewNote(TITLE, DESCRIPTION);
        ResultPage resultPage = new ResultPage(driver);
        resultPage.redirectToHome();
        notePage.navNotePage();
        assertEquals(TITLE, notePage.getTitle());
        assertEquals(DESCRIPTION, notePage.getDescription());
    }

    @Test
    public void  editCredential() {
        notePage.createNewNote(TITLE, DESCRIPTION);
        ResultPage resultPage = new ResultPage(driver);
        resultPage.redirectToHome();
        notePage.navNotePage();
        notePage.editNote("My note title");
        resultPage.redirectToHome();
        notePage.navNotePage();
        assertEquals("My note title", notePage.getTitle());
        assertEquals(DESCRIPTION, notePage.getDescription());
    }

    @Test
    public void  deleteCredential() {
        notePage.createNewNote(TITLE, DESCRIPTION);
        ResultPage resultPage = new ResultPage(driver);
        resultPage.redirectToHome();
        notePage.navNotePage();
        notePage.deleteNote();
        driver.switchTo().alert().accept();
        resultPage.redirectToHome();
        notePage.navNotePage();
        assertTrue(driver.findElements(By.id("tblNoteTitle")).isEmpty());
        assertTrue(driver.findElements(By.id("tblNoteDescription")).isEmpty());
    }
}
