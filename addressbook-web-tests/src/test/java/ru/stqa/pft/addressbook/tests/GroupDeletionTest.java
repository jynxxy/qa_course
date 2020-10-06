package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returnToGroupPage();
  }

}
