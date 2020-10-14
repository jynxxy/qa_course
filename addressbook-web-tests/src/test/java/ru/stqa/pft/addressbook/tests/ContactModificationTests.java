package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("test1", "surname1",
                    "789", "modemail@email.com", "test1"));
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("test1", "modsurname1",
                "789", "modemail@email.com", null), false);
        app.getContactHelper().submitContactModification();
    }
}