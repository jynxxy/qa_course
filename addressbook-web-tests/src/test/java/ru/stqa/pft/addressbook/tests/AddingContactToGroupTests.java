package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;

public class AddingContactToGroupTests extends TestBase {


    private Properties contact;
    private Properties group;

    @BeforeTest
    public void ensurePreconditions() throws IOException {
        Contacts contacts = app.db().contacts();
        contact = new Properties();
        String target = System.getProperty("target", "local");
        contact.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData()
                    .withName(group.getProperty("group.name"))
                    .withHeader(group.getProperty("group.header"))
                    .withFooter(group.getProperty("group.footer")));
            app.goTo().goToHomePage();
        }

        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().
                    withFirstName(contact.getProperty("contact.firstname"))
                    .withLastName(contact.getProperty("contact.lastname")));
            app.goTo().goToHomePage();
        }

        if (app.contact().findContactWithoutGroup(contacts) == null) {
            app.contact().create(new ContactData().
                    withFirstName(contact.getProperty("contact.firstname"))
                    .withLastName(contact.getProperty("contact.lastname")));
            app.goTo().goToHomePage();
        }

    }

    @Test
    public void testAddingContactToGroup() {
        app.goTo().goToHomePage();
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactData contactWithoutGroup = app.contact().findContactWithoutGroup(contacts);
        int contactId = contactWithoutGroup.getId();
        GroupData selectedGroup = groups.iterator().next();
        app.contact().addToGroup(contactWithoutGroup.getId(), selectedGroup.getId());

        Contacts contacts_after = app.db().getContactById(contactId);
        ContactData contactWithGroup = contacts_after.iterator().next();
        assertThat(contactWithGroup, CoreMatchers.equalTo(contactWithoutGroup.inGroup(selectedGroup)));
    }
}
