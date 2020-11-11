package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    private Properties contact;
    private Properties contact2;

    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        contact = new Properties();
        String target = System.getProperty("target", "local");
        contact.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().
                    withFirstName(contact.getProperty("contact.firstname"))
                    .withLastName(contact.getProperty("contact.lastname"))
                    .withHomePhone(contact.getProperty("contact.homePhone"))
                    .withEmail(contact.getProperty("contact.email")));
//                    .withGroup(contact.getProperty("contact.group")));
        }
    }

    @Test
    public void testContactModification() throws InterruptedException, IOException {
        contact2 = new Properties();
        String target = System.getProperty("target", "local");
        contact2.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();

        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName(contact2.getProperty("contact.modfirstname"))
                .withLastName(contact2.getProperty("contact.modlastname"))
                .withHomePhone(contact2.getProperty("contact.modhomePhone"))
                .withEmail(contact2.getProperty("contact.modemail"));
//                .withGroup(contact2.getProperty("contact.modgroup"));

        app.contact().modify(contact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}