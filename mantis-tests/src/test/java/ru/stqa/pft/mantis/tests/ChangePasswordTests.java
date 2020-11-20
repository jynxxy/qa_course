package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;


public class ChangePasswordTests extends TestBase {

    @BeforeClass
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePasswordByAdmin() throws InterruptedException, IOException, MessagingException {
        app.changePassword().loginAsAdmin();
        app.changePassword().clickManageUsers();

        Thread.sleep(3000);

        List<WebElement> user_list = app.getDriver().findElements(By.xpath("//a[contains(@href, 'manage_user_edit_page.php?user_')]"));

        int user_list_count = user_list.size();
        user_list.get(2).click();

        String username = app.getDriver().findElement(By.name("username")).getAttribute("value");
        String useremail = app.getDriver().findElement(By.name("email")).getAttribute("value");



        Thread.sleep(5000);
        app.getDriver().findElement(By.cssSelector("input[value='Reset Password']")).click();

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 100000);
        String changePasswordLink = findEmailToResetPassword(mailMessages, useremail);

        app.getDriver().get(changePasswordLink);
        app.changePassword().changePassword();
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
