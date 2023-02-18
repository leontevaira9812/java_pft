package ru.stqa.pft.adressbook;

import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    goToGroupPage();
    initNewGroup();
    fillGroupForm(new GroupData("grname", "logname", "comm"));
    submitGroupCreation();
    returnToGroupList();
    logout();
  }

}
