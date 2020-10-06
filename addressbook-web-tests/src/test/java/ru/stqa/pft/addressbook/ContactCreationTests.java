package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        goToAddNewEntry();
        fillAddressBook(new ContactData("name", "surname", "123456789", "test@test.com"));
    }

}
