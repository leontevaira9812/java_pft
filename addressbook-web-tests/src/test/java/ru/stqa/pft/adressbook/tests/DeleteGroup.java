package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;

import java.util.List;


public class DeleteGroup extends TestBase {

  @Test
  public void testDeleteGroup() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup(1);
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().deleteGroup();
    app.getGroupHelper().returnToGroupList();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);
    //  app.getSessionHelper().logout();

    before.remove(1);
    Assert.assertEquals(before, after);

  }


}
