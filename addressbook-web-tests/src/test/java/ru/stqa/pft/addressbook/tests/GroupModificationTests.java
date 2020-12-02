package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    private Properties group;
    private Properties group2;

    @BeforeMethod
    public void ensurePreconditions() {
        group = new Properties();

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(group.getProperty("group.name")));
        }
    }

    @Test
    public void testGroupModification() throws IOException {
        group2 = new Properties();
        String target = System.getProperty("target", "local");
        group2.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();

        GroupData group = new GroupData()
                .withId(modifiedGroup.getId())
                .withName(group2.getProperty("group.modname"))
                .withHeader(group2.getProperty("group.modheader"))
                .withFooter(group2.getProperty("group.modfooter"));
        app.goTo().groupPage();
        app.group().modify(group);
        Groups after = app.group().all();
        assertThat(app.group().count(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
        verifyGroupListInUI();
    }

}
