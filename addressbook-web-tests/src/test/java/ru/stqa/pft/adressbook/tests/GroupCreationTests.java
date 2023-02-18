package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().initNewGroup();
    app.getGroupHelper().fillGroupForm(new GroupData("grname", "logname", "comm"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupList();
    app.getSessionHelper().logout();
  }

}
