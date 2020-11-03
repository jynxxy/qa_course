package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts(){
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new ContactData().withFirstName("firstname1").withLastName("lastname1")
                .withHomePhone("987").withEmail("email1@test.com").withGroup("test 1")});
        list.add(new Object[] {new ContactData().withFirstName("firstname1").withLastName("lastname2")
                .withHomePhone("654").withEmail("email2@test.com").withGroup("test 1")});
        list.add(new Object[] {new ContactData().withFirstName("firstname3").withLastName("lastname3")
                .withHomePhone("321").withEmail("email3@test.com").withGroup("test 1")});
        return list.iterator();
    }

    @Test (dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {

        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/stru.jpg");
        app.goTo().AddNewContact();
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/stru.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
}
