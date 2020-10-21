package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getContactName());
        type(By.name("lastname"), contactData.getContactSurname());
        type(By.name("home"), contactData.getPhone());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void selectContact(int index) {
//        click(By.xpath("//input[@type='checkbox']"));                                 << old

        wd.findElements(By.xpath("//input[@name='selected[]']")).get(index).click();  //<< work with this
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void initContactModification() {
        click(By.xpath("//*[@id='maintable']/tbody/tr['+ index+ ']/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void returnToHomePage() {
        click(By.xpath("//a[contains(text(),'home')]"));
    }

    public void clickAddNew() {
        click(By.xpath("//a[contains(text(),'add new')]"));
    }

    public void createContact(ContactData contact) {
        clickAddNew();
        fillContactForm(contact, true);
        submitContactCreation();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return  isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.xpath("//*[@name='entry']"));
        for (WebElement element : elements) {

            String id = element.findElement(By.tagName("input")).getAttribute("value");
//            String contactname = element.findElement(By.xpath("./td[3]")).getText();
//            String contactsurname = element.findElement(By.xpath("./td[2]")).getText();
            String contactname = element.findElement(By.xpath("//*[@id='maintable']/tbody/tr['+index+']/td[3]")).getText();
            String contactsurname = element.findElement(By.xpath("//*[@id='maintable']/tbody/tr['+index+']/td[2]")).getText();
            ContactData contact = new ContactData(id, contactname, contactsurname,null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
