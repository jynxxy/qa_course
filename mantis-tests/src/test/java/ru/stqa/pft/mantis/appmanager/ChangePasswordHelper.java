package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

    public void login(String username, String password) {
        app.getDriver().get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Signup']"));
    }

    public void changePassword() {
        String newpassword = "newpassword";
        WebElement password = app.getDriver().findElement(By.name("password"));
        password.sendKeys(newpassword);
        app.getDriver().findElement(By.name("password_confirm")).sendKeys(newpassword);
        app.getDriver().findElement(By.cssSelector("input[value='Update User']")).click();
    }




}
