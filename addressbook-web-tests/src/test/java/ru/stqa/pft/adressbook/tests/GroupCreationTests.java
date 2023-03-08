package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;

import java.util.List;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //  int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().initNewGroup();
    GroupData group = new GroupData(0, "2", "logname", "comm");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupList();
    List<GroupData> after = app.getGroupHelper().getGroupList();

    // int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size() + 1);
    //  app.getSessionHelper().logout();
  }
}
   /* int max = 0;
    for (GroupData g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    group.setId(max);
    before.add(group);
    Assert.assertEquals(new HashSet<>(after), new HashSet<>(before));
  }
*/

