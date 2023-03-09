package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

@Test
public class GroupModification extends TestBase {
  public void testGroupModification() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().checkIsGroupExist(By.className("group"))) {
      app.getGroupHelper().initNewGroup();
      app.getGroupHelper().fillGroupForm(new GroupData(0, "grname", "logname", "comm"));
      app.getGroupHelper().submitGroupCreation();
      app.getGroupHelper().returnToGroupList();
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().editGroup();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(), "grnameModificatio1n", "logname", "comm");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupList();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //  int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    //Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }
}
