package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.goToAddNewEntry();
        app.fillAddressBook(new ContactData("name", "surname", "123456789", "test@test.com"));
    }

}
