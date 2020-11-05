package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactDetailsTest extends TestBase {

    @Test
    public void testContactDetails() {
        app.goTo().goToHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm_v2(contact);


        app.contact().clickDetails();
        String details = app.contact().details();

        String name = getAll(contactInfoFromEditForm);

        MatcherAssert.assertThat(details, equals(name));  //this is the first assertion I tried to use
        Assert.assertEquals(details, name);               //this is the second assertion I tried to use
    }

    private String getAll(ContactData contact) {
        return  Arrays.asList(paragraph_1(contact), paragraph_2(contact), paragraph_3(contact), paragraph_4(contact))
                .stream().filter((s -> !s.equals("")))
                .map(ContactDetailsTest::clean)
                .collect(Collectors.joining(""));
    }

    public static String clean (String cleanedDetails) {
        return cleanedDetails.replaceAll("\n", "");
    }

    private boolean iaEmpty(String s) {
        return s == null || "".equals(s);
    }

    private boolean isNotEmpty(String s) {
        return s != null && !"".equals(s);
    }

    private <T> String paragraph_1(ContactData contact) {
        return Arrays.asList(contact.getFirstName(), contact.getMiddlename(), contact.getLastName())
                .stream().filter(this::isNotEmpty)
                .collect(Collectors.joining(" "));
    }

    private String paragraph_2(ContactData contact) {
        return  Arrays.asList(contact.getNick(), contact.getTitle(), contact.getCompany(), contact.getAddress())
                .stream().filter(this::isNotEmpty)
                .collect(Collectors.joining("\n"));
    }

    private <T> String paragraph_3(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getFax())
                .stream().filter(this::isNotEmpty)
                .collect(Collectors.joining("\n"));
    }

    private <T> String paragraph_4(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
                contact.getHomepage(), "\n\n" + contact.getAddress2(), contact.getPhone2(),
                "\n" + contact.getNotes())
                .stream().filter(this::isNotEmpty)
                .collect(Collectors.joining("\n"));
    }

}
