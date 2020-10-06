package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    goToAddNewEntry();
    fillAddressBook(new ContactData("name", "surname", "123456789", "test@test.com"));
  }

}
