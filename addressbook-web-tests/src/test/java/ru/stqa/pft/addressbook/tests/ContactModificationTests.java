package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillAddressBook(new ContactData("modname1", "modsurname1", "789", "modemail@email.com"));
        app.getContactHelper().submitContactModification();
    }
}