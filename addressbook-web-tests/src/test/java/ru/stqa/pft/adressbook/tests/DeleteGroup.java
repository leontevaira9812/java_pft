package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Groups after = app.group().all();
    Assert.assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.withoutAdded(deletedGroup)));
  }


}
