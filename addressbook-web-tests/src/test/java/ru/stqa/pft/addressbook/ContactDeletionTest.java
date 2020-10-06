package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testUserDeletion() {
    selectContactToDelete();
    deleteSelectedContacts();
  }

}
