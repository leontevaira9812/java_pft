package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

@Test
public class GroupModification extends TestBase {
  public void testGroupModification() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup(1);
    //int before = app.getGroupHelper().getGroupCount();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().editGroup();
    GroupData group = new GroupData(before.get(1).getId(), "grname", "logname", "comm");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupList();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //  int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size());

    before.remove(1);
    before.add(group);
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }
}
