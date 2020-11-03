package ru.stqa.pft.addressbook.tests;

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
        contactInfoFromEditForm.toString();

        app.contact().clickDetails();
        app.contact().details();

//        ContactData contactDetailsInfo = app.contact().contactDetailsInfo(contact);

//        MatcherAssert.assertThat(contact.withDetails(contact.getDetails()), CoreMatchers.equalTo(mergeDetailsFromEditForm(contactInfoFromEditForm)));

//        String name = paragraph_1(contact) + "\n" + paragraph_2(contact) + "\n" + paragraph_3(contact) + "\n" + paragraph_4(contact);


//        MatcherAssert.assertThat(contact.getDetails(), equals(getAll(contactInfoFromEditForm)));
    }

    private String getAll(ContactData contact) {
        return  Arrays.asList(paragraph_1(contact), paragraph_2(contact), paragraph_3(contact), paragraph_4(contact))
                .stream().filter((s -> !s.equals("")))
                .collect(Collectors.joining("\n"));
    }


    private <T> String paragraph_1(ContactData contact) {
        return Arrays.asList(contact.getFirstName(), contact.getMiddlename(), contact.getLastName())
                .stream().filter((s -> !s.equals("")))
                .collect(Collectors.joining(" "));
    }

    private String paragraph_2(ContactData contact) {
        return  Arrays.asList(paragraph_1(contact), contact.getNick(), contact.getTitle(),
                contact.getCompany(), contact.getAddress())
                .stream().filter((s -> !s.equals("")))
                .collect(Collectors.joining("\n"));
    }

    private <T> String paragraph_3(ContactData contact) {
        return Arrays.asList(paragraph_2(contact), contact.getHomePhone(), contact.getMobilePhone(),
                contact.getWorkPhone(), contact.getFax())
                .stream().filter((s -> !s.equals("")))
                .collect(Collectors.joining("\n"));
    }

    private <T> String paragraph_4(ContactData contact) {
        return Arrays.asList(paragraph_3(contact), contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s -> !s.equals("")))
                .collect(Collectors.joining("\n"));
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
