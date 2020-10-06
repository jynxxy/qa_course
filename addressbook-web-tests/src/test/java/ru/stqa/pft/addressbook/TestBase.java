package ru.stqa.pft.addressbook;

import org.openqa.selenium.firefox.FirefoxDriver;
<<<<<<< HEAD:addressbook-web-tests/src/test/java/ru/stqa/pft/addressbook/appmanager/ApplicationManager.java
=======
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
>>>>>>> parent of db02edc... Converting inheritance to delegation and creating a two-tier architecture:addressbook-web-tests/src/test/java/ru/stqa/pft/addressbook/TestBase.java

import java.util.concurrent.TimeUnit;

public class TestBase {
    FirefoxDriver wd;

<<<<<<< HEAD:addressbook-web-tests/src/test/java/ru/stqa/pft/addressbook/appmanager/ApplicationManager.java
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;

    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }

=======
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
      wd = new FirefoxDriver();
      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      wd.get("http://localhost/addressbook/");
      login("admin", "secret");
    }

    private void login(String username, String password) {
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys(username);
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    protected void returnToGroupPage() {
      wd.findElement(By.linkText("group page")).click();
    }

    protected void submitGroupCreation() {
      wd.findElement(By.name("submit")).click();
    }

    protected void fillGroupForm(GroupData groupData) {
      wd.findElement(By.name("group_name")).click();
      wd.findElement(By.name("group_name")).clear();
      wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
      wd.findElement(By.name("group_header")).click();
      wd.findElement(By.name("group_header")).clear();
      wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
      wd.findElement(By.name("group_footer")).click();
      wd.findElement(By.name("group_footer")).clear();
      wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    protected void initGroupCreation() {
      wd.findElement(By.name("new")).click();
    }

    protected void goToGroupPage() {
      wd.findElement(By.linkText("groups")).click();
    }
>>>>>>> parent of db02edc... Converting inheritance to delegation and creating a two-tier architecture:addressbook-web-tests/src/test/java/ru/stqa/pft/addressbook/TestBase.java

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
      wd.quit();
    }

<<<<<<< HEAD:addressbook-web-tests/src/test/java/ru/stqa/pft/addressbook/appmanager/ApplicationManager.java
    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
=======
    protected void deleteSelectedGroup() {
      wd.findElement(By.name("delete")).click();
    }

    protected void selectGroup() {
      wd.findElement(By.name("selected[]")).click();
>>>>>>> parent of db02edc... Converting inheritance to delegation and creating a two-tier architecture:addressbook-web-tests/src/test/java/ru/stqa/pft/addressbook/TestBase.java
    }
}
