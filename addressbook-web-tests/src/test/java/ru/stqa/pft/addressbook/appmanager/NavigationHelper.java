package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper {
    private FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd) {

    }

    public void goToGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    public void goToAddNewEntry() {
        wd.findElement(By.linkText("add new")).click();
    }
}
