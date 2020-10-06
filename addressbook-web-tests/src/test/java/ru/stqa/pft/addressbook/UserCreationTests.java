package ru.stqa.pft.addressbook;

<<<<<<< HEAD:addressbook-web-tests/src/test/java/ru/stqa/pft/addressbook/ContactCreationTests.java
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.tests.TestBase;

<<<<<<< HEAD
public class ContactCreationTests extends TestBase {
=======
public class ContactCreationTests {
=======
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UserCreationTests {
>>>>>>> parent of 80bebe6... refactoring of contactCreationTests.java:addressbook-web-tests/src/test/java/ru/stqa/pft/addressbook/UserCreationTests.java
  FirefoxDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    login();
  }
>>>>>>> parent of 07153a7... update ContactCreationTests class and GriupCreationTests class

  @Test
  public void testUserCreationTests() throws Exception {
    goToAddNewEntry();
    fillAddressBook();
  }

<<<<<<< HEAD:addressbook-web-tests/src/test/java/ru/stqa/pft/addressbook/ContactCreationTests.java
=======
  private void fillAddressBook() {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys("name");
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys("surname");
    wd.findElement(By.name("home")).click();
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys("123456789");
    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys("test@test.com");
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  private void goToAddNewEntry() {
    wd.findElement(By.linkText("add new")).click();
  }

  private void login() {
    wd.get("http://localhost/addressbook/");
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys("admin");
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys("secret");
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();
  }
>>>>>>> parent of 80bebe6... refactoring of contactCreationTests.java:addressbook-web-tests/src/test/java/ru/stqa/pft/addressbook/UserCreationTests.java
}
