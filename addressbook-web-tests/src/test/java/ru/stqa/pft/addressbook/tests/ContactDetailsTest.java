package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.time.Year;
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

        MatcherAssert.assertThat(details, CoreMatchers.equalTo(name));
    }

    private String getAll(ContactData contact) {
       return Arrays.asList(paragraph_1(contact), paragraph_2(contact), paragraph_3(contact),
                paragraph_4(contact), paragraph_5(contact))
                .stream().filter((s -> !s.equals("")))
                .map(ContactDetailsTest::clean)
                .collect(Collectors.joining(""));

    }

    public static String clean(String cleanedDetails) {
        return cleanedDetails.replaceAll("\n", "");
    }

    private boolean iaEmpty(String s) {
        return s == null || "".equals(s);
    }

    private boolean isNotEmpty(String s) {
        return s != null && !"".equals(s);
    }

    private String paragraph_1(ContactData contact) {
        return Arrays.asList(contact.getFirstName(), contact.getMiddlename(), contact.getLastName())
                .stream().filter(this::isNotEmpty)
                .collect(Collectors.joining(" "));
    }

    private String paragraph_2(ContactData contact) {
        return Arrays.asList(contact.getNick(), contact.getTitle(), contact.getCompany(), contact.getAddress())
                .stream().filter(this::isNotEmpty)
                .collect(Collectors.joining("\n"));
    }

    private String paragraph_3(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
                contact.getFax(), contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
                contact.getHomepage())
                .stream().filter(this::isNotEmpty)
                .map(ContactDetailsTest::cleanDomain)
                .collect(Collectors.joining("\n"));
    }

    private String paragraph_4(ContactData contact) {
        String year = contact.getBirthday_year();
        String anniversary = contact.getAnniversary_year();
        return Arrays.asList(" " + contact.getBirthday_day(), contact.getBirthday_month(),
                year, countAge(year), contact.getAnniversary_day(), contact.getAnniversary_month(),
                anniversary, countAnniversary(anniversary))
                .stream().filter(this::isNotEmpty)
                .map(ContactDetailsTest::cleanDate)
                .collect(Collectors.joining(" "));
    }

    private String paragraph_5(ContactData contact) {
        return Arrays.asList(contact.getAddress2(), contact.getPhone2(), contact.getNotes())
                .stream().filter(this::isNotEmpty)
                .collect(Collectors.joining("\n"));
    }

    private String countAge(String year) {
        String actualAge = "";
        if (year != null && !year.equals("")) {
            int yearInt = Integer.parseInt(year);
            int actualYear = Year.now().getValue();
            int actualAgeInt = actualYear - yearInt;
            actualAge = Integer.toString(actualAgeInt);
        }
        return actualAge;
    }

    private String countAnniversary(String years) {
        String actualAnniversary = "";
        if (years != null && !years.equals("")) {
            int yearsInt = Integer.parseInt(years);
            int actualYear = Year.now().getValue();
            int actualAnniversaryInt = actualYear - yearsInt;
            actualAnniversary = Integer.toString(actualAnniversaryInt);
        }
        return actualAnniversary;
    }

    public static String cleanDate(String cleanDate) {
        return cleanDate.replaceAll("-", "");
    }

    public static String cleanDomain(String cleanDomain) {
        return cleanDomain.replaceAll("\\.", "");
    }
}
