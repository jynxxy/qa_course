package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class ChangePasswordHelper extends HelperBase {

    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void loginAsAdmin() {
        wd.get(app.getProperty("web.baseUrl"));
        type(By.name("username"), app.getProperty("web.adminLogin"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.xpath("//input[@class='button']"));
    }

    public void clickManageUsers() {
        click(By.xpath("//a[contains(@href, '/mantisbt-1.2.20/manage_overview_page.php')]"));
        click(By.xpath("//a[contains(text(),'Manage Users')]"));
    }

    public void selectUser(UserData account) {
        click(By.cssSelector("a[href='manage_user_edit_page.php?user_id="+ account.getId()+"']"));
    }

    public void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

}
