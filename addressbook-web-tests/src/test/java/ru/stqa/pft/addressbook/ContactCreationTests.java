package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.tests.TestBase;

<<<<<<< HEAD
public class ContactCreationTests extends TestBase {
=======
public class ContactCreationTests {
  FirefoxDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    login();
  }
>>>>>>> parent of 07153a7... update ContactCreationTests class and GriupCreationTests class

  @Test
  public void testContactCreation() throws Exception {
    goToAddNewEntry();
    fillAddressBook(new ContactData("name", "surname", "123456789", "test@test.com"));
  }

}
