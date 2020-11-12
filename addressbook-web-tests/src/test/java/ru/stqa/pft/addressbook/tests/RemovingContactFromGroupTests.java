package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class RemovingContactFromGroupTests extends TestBase {

//    private Properties contact;
//    private Properties group;
//
//    @BeforeMethod
//    public void ensurePreconditions() throws IOException {
//        contact = new Properties();
//        String target = System.getProperty("target", "local");
//        contact.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
//
//        if (app.db().contacts().size() == 0) {
//            app.goTo().contactPage();
//            app.contact().create(new ContactData().
//                    withFirstName(contact.getProperty("contact.firstname"))
//                    .withLastName(contact.getProperty("contact.lastname")));
//        }
//        else if (app.db().groups().size() == 0) {
//            app.goTo().groupPage();
//            app.group().create(new GroupData().withName(group.getProperty("group.name")));
//        }
//    }

    @Test
    public void testRemovingContactFromGroup() throws InterruptedException {
        app.goTo().goToHomePage();
        //        Contacts before = app.db().contacts();
        ContactData contact = app.contact().all().iterator().next();
        Thread.sleep(5000);

        app.contact().removeFromGroup(contact);
        Thread.sleep(5000);



    }

}
