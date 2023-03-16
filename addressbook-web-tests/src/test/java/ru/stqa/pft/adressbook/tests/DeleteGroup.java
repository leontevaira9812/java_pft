package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;

import java.util.List;


public class DeleteGroup extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (!app.group().checkIsGroupExist(By.className("group"))) {
      //app.group().create(new GroupData(0, "grname", "logo", "comm"));
      app.group().create(new GroupData().withName("grname").withLogo("logo").withComment("comm"));
    }
  }

  @Test
  public void testDeleteGroup() throws Exception {
    List<GroupData> before = app.group().list();
    app.group().delete(before);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() - 1);
    //  app.getSessionHelper().logout();

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);

  }


}
