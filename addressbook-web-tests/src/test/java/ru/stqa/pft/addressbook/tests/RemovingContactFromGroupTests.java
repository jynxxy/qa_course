package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
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

public class RemovingContactFromGroupTests extends TestBase {

    private Properties contact;
    private Properties group;

    @BeforeMethod
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
    public void testRemovingContactFromGroup() {
        app.goTo().goToHomePage();
        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData deletedContact = before.iterator().next();
        GroupData groupFrom = groups.iterator().next();
        int contactId = deletedContact.getId();
        String groupName = groupFrom.getName();
        app.contact().removeFromGroup(groupName, contactId);
        app.goTo().goToHomePage();
        Assert.assertEquals(app.contact().countContacts(), before.size()-1);
    }

}
