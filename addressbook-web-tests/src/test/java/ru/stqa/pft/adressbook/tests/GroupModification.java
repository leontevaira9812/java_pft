package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;

@Test
public class GroupModification extends TestBase {
  public void testGroupModification() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().editGroup();
    app.getGroupHelper().fillGroupForm(new GroupData("grname", "logname", "comm"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupList();
  }
}
