package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class ChangePasswordTests extends TestBase {

    @BeforeClass
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePasswordByAdmin() throws IOException, MessagingException, InterruptedException {
        Users users = app.db().users();
        UserData selectedContact = users.iterator().next();
        app.changePassword().loginAsAdmin();
        app.changePassword().clickManageUsers();


        app.changePassword().loginAsAdmin();
        app.changePassword().clickManageUsers();

        Thread.sleep(3000);

        List<WebElement> user_list = app.getDriver().findElements(By.xpath("//a[contains(@href, 'manage_user_edit_page.php?user_')]"));

        int user_list_count = user_list.size();
        user_list.get(2).click();

        String username = app.getDriver().findElement(By.name("username")).getAttribute("value");
        String useremail = app.getDriver().findElement(By.name("email")).getAttribute("value");

        app.getDriver().findElement(By.cssSelector("input[value='Reset Password']")).click();

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 100000);
        String changePasswordLink = findEmailToResetPassword(mailMessages, useremail);

        app.getDriver().get(changePasswordLink);
        String newpassword = "newpassword";
        WebElement password = app.getDriver().findElement(By.name("password"));
        password.sendKeys(newpassword);
        app.getDriver().findElement(By.name("password_confirm")).sendKeys(newpassword);
        app.getDriver().findElement(By.cssSelector("input[value='Update User']")).click();

        app.getDriver().get("http://localhost/mantisbt-1.2.20/login_page.php");
        app.getDriver().findElement(By.name("username")).sendKeys(username);
        app.getDriver().findElement(By.name("password")).sendKeys(newpassword);

    }

    private String findEmailToResetPassword(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}
