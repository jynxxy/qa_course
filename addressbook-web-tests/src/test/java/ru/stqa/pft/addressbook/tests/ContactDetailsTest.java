package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDetailsTest extends TestBase {

    @Test
    public void testContactDetails() throws InterruptedException {
        app.goTo().goToHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        app.contact().clickDetails();
        ContactData contactDetailsInfo = app.contact().contactDetailsInfo(contact);

        MatcherAssert.assertThat(contactDetailsInfo, CoreMatchers.equalTo(contactInfoFromEditForm));
    }

}
