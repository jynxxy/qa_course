package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().goToAddNewEntry();
        app.getContactHelper().fillAddressBook(new ContactData("name2", "surname", "444", "test@test.com"));
        app.getContactHelper().submitContactCreation();
    }

}
