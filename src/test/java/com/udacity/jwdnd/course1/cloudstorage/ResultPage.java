package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    @FindBy(id = "resultSuccessLink")
    private WebElement resultSuccessLink;
    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public void redirectToHome() {
        this.resultSuccessLink.click();
    }
}
