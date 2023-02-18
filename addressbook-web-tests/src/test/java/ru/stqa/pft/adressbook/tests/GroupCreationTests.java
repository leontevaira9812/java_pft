package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goToGroupPage();
    app.initNewGroup();
    app.fillGroupForm(new GroupData("grname", "logname", "comm"));
    app.submitGroupCreation();
    app.returnToGroupList();
    app.logout();
  }

}
