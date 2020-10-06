package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {


    @Test
    public void testUserDeletion() throws Exception {

        selectContact();
        deleteContact();
    }


}
