package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotePage {
    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "addNewNoteBtn")
    private WebElement addNewNoteButton;

    @FindBy(id = "editNoteBtn")
    private WebElement editNoteButton;

    @FindBy(id = "deleteNoteBtn")
    private WebElement deleteNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "saveChangesButton")
    private WebElement saveChangesButton;

    @FindBy(id = "noteTitle")
    private WebElement tblNoteTitle;

    @FindBy(id = "noteDescription")
    private WebElement tblNoteDescription;

    WebDriverWait webDriverWait;
    public NotePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        webDriverWait = new WebDriverWait(driver, 1000);
    }

    public void navNotePage(){
        this.navNotesTab.click();
    }

    public void createNewNote(String title, String description){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("addNewNoteBtn")));
        this.addNewNoteButton.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("note-title")));
        this.noteTitle.sendKeys(title);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("note-description")));
        this.noteDescription.sendKeys(description);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("saveChangesButton")));
        this.saveChangesButton.click();
    }

    public void editNote(String title){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("editNoteBtn")));
        this.editNoteButton.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("note-title")));
        this.noteTitle.clear();
        this.noteTitle.sendKeys(title);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("saveChangesButton")));
        this.saveChangesButton.click();
    }

    public void deleteNote(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("deleteNoteBtn")));
        this.deleteNoteButton.click();
    }
    public String getTitle(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("noteTitle")));
        return this.tblNoteTitle.getText();
    }

    public String getDescription(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("noteDescription")));
        return this.tblNoteDescription.getText();
    }

}
