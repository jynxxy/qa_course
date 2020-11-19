package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.subethamail.wiser.Wiser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static ru.stqa.pft.mantis.tests.TestBase.app;

public class ChangePasswordTests {


    WebDriver wd = new ChromeDriver();

    @BeforeClass
    public void startMailServer() {
        app.mail().start();
    }


    @Test
    public void testChangePasswordByAdmin() throws InterruptedException, IOException, MessagingException {
        wd.get("http://localhost/mantisbt-1.2.20/login_page.php");
        wd.findElement(By.name("username")).sendKeys("administrator");
        wd.findElement(By.name("password")).sendKeys("root");
        wd.findElement(By.xpath("//input[@class='button']")).click();

        wd.findElement(By.xpath("//a[contains(text(),'Manage Users')]")).click();
        Thread.sleep(3000);
        List<WebElement> user_list = wd.findElements(By.xpath("//a[contains(@href, 'manage_user_edit_page.php?user_')]"));
        int user_list_count = user_list.size();
        user_list.get(1).click();
        String username = wd.findElement(By.name("username")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        System.out.println("Username: " + username + "Email: " + email);
        Thread.sleep(3000);
        wd.findElement(By.xpath("//a[contains(text(),'Reset Password')]")).click();

        long now = System.currentTimeMillis();



        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String changePasswordLink = findEmailToResetPassword(mailMessages, email);
        wd.get(changePasswordLink);

        wd.quit();
    }

    private String findEmailToResetPassword(List<MailMessage> mailMessages, String link) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(link)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }


}
