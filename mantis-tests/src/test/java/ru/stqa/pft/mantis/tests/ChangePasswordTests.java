package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.subethamail.wiser.Wiser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;



public class ChangePasswordTests extends TestBase {

    private final Wiser wiser;

    private ChangePasswordTests() {
        wiser = new Wiser();

    }

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
        user_list.get(1).click();
        long now = System.currentTimeMillis();
        String username = app.getDriver().findElement(By.name("username")).getAttribute("value");
        String useremail = app.getDriver().findElement(By.name("email")).getAttribute("value");

        String email = String.format(useremail + now);

        Thread.sleep(5000);
        app.getDriver().findElement(By.cssSelector("input[value='Reset Password']"));

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String changePasswordLink = findEmailToResetPassword(mailMessages, email);

        app.getDriver().get(changePasswordLink);
        String newpassword = "newpassword";
        WebElement password = app.getDriver().findElement(By.name("password"));
        password.sendKeys(newpassword);
        app.getDriver().findElement(By.name("confirm_password")).sendKeys(newpassword);
        app.getDriver().findElement(By.cssSelector("input[value='Update user']"));

//        app.getDriver().quit();
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
