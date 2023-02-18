package ru.stqa.pft.adressbook;

import org.testng.annotations.Test;


public class DeleteGroup extends TestBase {

  @Test
  public void testDeleteGroup() throws Exception {
    goToGroupPage();
    selectGroup();
    deleteGroup();
    returnToGroupList();
    logout();
  }


}
