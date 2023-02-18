package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;


public class DeleteGroup extends TestBase {

  @Test
  public void testDeleteGroup() throws Exception {
    app.goToGroupPage();
    app.selectGroup();
    app.deleteGroup();
    app.returnToGroupList();
    app.logout();
  }


}
