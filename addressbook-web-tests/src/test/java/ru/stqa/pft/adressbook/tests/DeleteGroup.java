package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;

import java.util.List;


public class DeleteGroup extends TestBase {

  @Test
  public void testDeleteGroup() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().checkIsGroupExist(By.className("group"))) {
      app.getGroupHelper().initNewGroup();
      app.getGroupHelper().fillGroupForm(new GroupData(0, "grname", "logname", "comm"));
      app.getGroupHelper().submitGroupCreation();
      app.getGroupHelper().returnToGroupList();
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteGroup();
    app.getGroupHelper().returnToGroupList();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);
    //  app.getSessionHelper().logout();

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);

  }


}
