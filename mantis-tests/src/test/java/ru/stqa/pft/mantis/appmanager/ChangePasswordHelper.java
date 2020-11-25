package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase {

    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void loginAsAdmin() {
        app.getDriver().get("http://localhost/mantisbt-1.2.20/login_page.php");
        app.getDriver().findElement(By.name("username")).sendKeys("administrator");
        app.getDriver().findElement(By.name("password")).sendKeys("root");
        app.getDriver().findElement(By.xpath("//input[@class='button']")).click();
    }

    public void clickManageUsers() {
        app.getDriver().findElement(By.xpath("//a[contains(text(),'Manage Users')]")).click();
    }

}
