package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        int before = app.getContactHelper().getContactCount();
        app.getNavigationHelper().goToAddNewEntry();
        app.getContactHelper().createContact(new ContactData("name3", "surname",
                "444", "test@test.com", "test1"));
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 1);
    }

}
