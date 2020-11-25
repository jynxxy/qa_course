package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

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
        ContactData contact = app.contact().all().iterator().next();
        app.contact().clickDetails();
        app.contact().isContactBelongsToGroup();
        app.contact().addToGroup(contact);
    }
}
