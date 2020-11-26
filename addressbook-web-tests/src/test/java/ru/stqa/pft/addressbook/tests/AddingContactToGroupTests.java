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
        contact = new Properties();
        String target = System.getProperty("target", "local");
        contact.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().
                    withFirstName(contact.getProperty("contact.firstname"))
                    .withLastName(contact.getProperty("contact.lastname")));
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName(group.getProperty("group.name"))
                    .withHeader(group.getProperty("group.header"))
                    .withFooter(group.getProperty("group.footer")));
        }
    }


    @Test
    public void testAddingContactToGroup() {
        app.goTo().goToHomePage();
        ContactData contact = app.db().contacts().iterator().next();
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        app.goTo().insideGroup(groups.iterator().next().getName());
        if (app.group().isThereContact()) {
            app.contact().deleteContactFromGroup(contact);
        }

        GroupData group = groups.iterator().next();
        String groupName = group.getName();
        groups.removeAll(contact.getGroups());
        app.goTo().goToHomePage();
        app.contact().addToGroup(groupName);

        app.contact().isContactBelongsToGroup();
        app.contact().addToGroup(groupName);
        app.db().refresh(contact);

        Groups groups_after = app.db().groups();
        Contacts contacts_after = app.db().contacts();

        assertThat(groups, CoreMatchers.equalTo(groups_after));
        assertThat(contacts, CoreMatchers.equalTo(contacts_after));
    }
}
