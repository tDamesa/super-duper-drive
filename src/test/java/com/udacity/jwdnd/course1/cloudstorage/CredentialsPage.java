package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialsPage {
    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy(id = "addNewCredential")
    private WebElement addNewCredentialButton;

    @FindBy(id = "editCredentialBtn")
    private WebElement editCredentialBtn;

    @FindBy(id = "deleteCredentialBtn")
    private WebElement deleteCredentialBtn;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "saveChanges")
    private WebElement saveChangesButton;

    @FindBy(id = "tblCredentialUrl")
    private WebElement tblCredentialUrl;

    @FindBy(id = "tblCredentialUsername")
    private WebElement tblCredentialUsername;

    @FindBy(id = "tblCredentialPassword")
    private WebElement tblCredentialPassword;

    WebDriverWait webDriverWait;
    public CredentialsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        webDriverWait = new WebDriverWait(driver, 1000);
    }

    public void navCredentialsPage(){
        this.navCredentialsTab.click();
    }

    public void createNewCredential(String username, String password, String url){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("addNewCredential")));
        this.addNewCredentialButton.click();
        addCredentialsInfo(username, password, url);
    }

    private void addCredentialsInfo(String username, String password, String url) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("credential-username")));
        this.credentialUsername.sendKeys(username);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("credential-password")));
        this.credentialPassword.sendKeys(password);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("credential-url")));
        this.credentialUrl.sendKeys(url);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("saveChanges")));
        this.saveChangesButton.click();
    }

    public void editCredential(String username){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("editCredentialBtn")));
        this.editCredentialBtn.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("credential-username")));
        this.credentialUsername.clear();
        this.credentialUsername.sendKeys(username);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("saveChanges")));
        this.saveChangesButton.click();
    }

    public void deleteCredential(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("editCredentialBtn")));
        this.deleteCredentialBtn.click();
    }
    public String getUrl(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("tblCredentialUrl")));
        return this.tblCredentialUrl.getText();
    }

    public String getUsername(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("tblCredentialUsername")));
        return this.tblCredentialUsername.getText();
    }

    public String getPassword(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("tblCredentialPassword")));
        return this.tblCredentialPassword.getText();
    }


}
