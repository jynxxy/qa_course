package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactDetailsTest extends TestBase {

    @Test
    public void testContactDetails() throws InterruptedException {
        app.goTo().goToHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        app.contact().clickDetails();
        app.contact().details();

//        ContactData contactDetailsInfo = app.contact().contactDetailsInfo(contact);

//        MatcherAssert.assertThat(contact.withDetails(contact.getDetails()), CoreMatchers.equalTo(mergeDetailsFromEditForm(contactInfoFromEditForm)));




        String name = fullName(contact) + "\n" + mergePhones(contact) + "\n" + mergePhones(contact);

    }

    private String getAll(ContactData contact) {
        return  Arrays.asList(paragraph1(contact))
                .stream().filter((s -> !s.equals("")))
                .collect(Collectors.joining("\n"));
    }


    private <T> String fullName(ContactData contact) {
        return Arrays.asList(contact.getFirstName(), contact.getMiddlename(), contact.getLastName())
                .stream().filter((s -> !s.equals("")))
                .collect(Collectors.joining(" "));
    }

    private String paragraph1(ContactData contact) {
        return  Arrays.asList(fullName(contact), contact.getNick(), contact.getTitle(),
                contact.getCompany(), contact.getAddress())
                .stream().filter((s -> !s.equals("")))
                .collect(Collectors.joining("\n"));
    }

    private <T> String address1(ContactData contact) {
        return Arrays.asList(contact.getAddress())
                .stream().filter((s -> !s.equals("")))
                .collect(Collectors.joining(" "));
    }

    private <T> String address2(ContactData contact) {
        return Arrays.asList(contact.getAddress2())
                .stream().filter((s -> !s.equals("")))
                .collect(Collectors.joining(" "));
    }

    private <T> String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactDetailsTest::clean)
                .collect(Collectors.joining("\n"));
    }

    private <T> String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactDetailsTest::clean)
                .collect(Collectors.joining("\n"));
    }



    private String mergeDetailsFromEditForm (ContactData contact) {
        return  Arrays.asList(contact.getFirstName(), contact.getLastName(), contact.getAddress(),
                contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
                contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public static String clean (String detail ) {
        return detail.replaceAll("\\s", "").replaceAll("[-()]","");
    }

}
