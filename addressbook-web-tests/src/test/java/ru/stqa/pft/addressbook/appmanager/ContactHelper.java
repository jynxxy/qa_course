package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Arrays;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("email"), contactData.getEmail());
//        attach(By.name("photo"), contactData.getPhoto());

//        if (creation) {
//            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
//        } else {
//            Assert.assertFalse(isElementPresent(By.name("new_group")));
//        }
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void initContactModification() {
        List<WebElement> mylist = wd.findElements(By.cssSelector("img[alt='Edit']"));
        mylist.get(mylist.size() - 1).click();
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

    public void clickDetails() {
        click(By.cssSelector("img[alt='Details']"));
    }

    public void create(ContactData contact) {
        clickAddNew();
        fillContactForm(contact, true);
        submitContactCreation();
        returnToHomePage();
    }

    public void modify(ContactData contact) throws InterruptedException {
        selectContactById(contact.getId());
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        returnToHomePage();
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname)
                    .withAddress(address)
                    .withEmail(allEmails)
                    .withAllPhones(allPhones));
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withAddress(address)
                .withEmail(email).withEmail2(email2).withEmail3(email3)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

//        WebElement check2 = wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id))).click();
//        WebElement check3 = wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id)));
//        WebElement check4 = wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id)));
    }

    public ContactData contactDetailsInfo(ContactData contactDetails) {

        WebElement element = wd.findElement(By.id("content"));
        String contentText = element.getText();
        List<String> contentLines = Arrays.asList(contentText.split("\n"));
        String[] name = contentLines.get(0).split(" ");
        String firstname = name[0];
        String lastname = name[1];
        String address = contentLines.get(1);
        String home = contentLines.get(3).replaceAll("H: ", "");
        String work = contentLines.get(4).replaceAll("W: ", "");
        String email = contentLines.get(6);

        return new ContactData().withId(contactDetails.getId()).
                withFirstName(firstname).withLastName(lastname).withAddress(address)
                .withHomePhone(home).withWorkPhone(work).withEmail(email);


    }


    public void test() {
        String text = wd.findElement(By.xpath("/html/body/div/div[4]")).getAttribute("innerText");
        System.out.println(text);
//        text.replaceAll("\n\n", "\n");
//        System.out.println(text.replaceAll("\n\n", "").replaceAll("\\s", "\n"));
    }

}
