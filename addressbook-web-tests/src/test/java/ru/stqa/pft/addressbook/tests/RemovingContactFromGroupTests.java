package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemovingContactFromGroupTests extends TestBase {

    private Properties contact;
    private Properties group;

    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        Contacts contacts = app.db().contacts();
        contact = new Properties();
        String target = System.getProperty("target", "local");
        contact.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName(group.getProperty("group.name"))
                    .withHeader(group.getProperty("group.header"))
                    .withFooter(group.getProperty("group.footer")));
            app.goTo().goToHomePage();
        }

        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().
                    withFirstName(contact.getProperty("contact.firstname"))
                    .withLastName(contact.getProperty("contact.lastname")));
            app.goTo().goToHomePage();
        }

        if (app.contact().findContactWithGroup(contacts) == null) {
            app.contact().create(new ContactData().
                    withFirstName(contact.getProperty("contact.firstname"))
                    .withLastName(contact.getProperty("contact.lastname")));
            app.goTo().goToHomePage();
        }

    }

    @Test
    public void testRemovingContactFromGroup() {
        app.goTo().goToHomePage();
        Contacts contacts = app.db().contacts();
        ContactData contactWithGroup = app.contact().findContactWithGroup(contacts);
        int contactId = contactWithGroup.getId();
        GroupData group = contactWithGroup.getGroups().iterator().next();
        int groupId = group.getId();
        Groups deletedGroup = app.db().getGroupById(groupId);
        GroupData deletedGroupData = deletedGroup.iterator().next();
        app.contact().filterByGroup(groupId);
        app.contact().removeFromGroup(contactWithGroup.getId(), group.getId());

        Contacts contacts_after = app.db().getContactById(contactId);
        ContactData contactWithoutGroup = contacts_after.iterator().next();
        assertThat(contactWithGroup, equalTo(contactWithoutGroup.inGroup(deletedGroupData)));
    }

}
