package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().goToAddNewEntry();
        app.getGroupHelper().fillAddressBook(new ContactData("name", "surname", "123456789", "test@test.com"));
    }

}
