package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;

import java.util.Set;


@Test
public class GroupModification extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    // if (app.group().list().size() == 0) {    }
    if (!app.group().checkIsGroupExist(By.className("group"))) {
      //app.group().create(new GroupData(0, "grname", "logo", "comm"));
      app.group().create(new GroupData().withName("grname").withLogo("logo").withComment("comm"));
    }
  }

  public void testGroupModification() throws Exception {
    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    //GroupData group = new GroupData(before.get(before.size() - 1).getId(), "grnameModificatio1n", "logname", "comm");
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("grnameModificatio1n23").
            withComment("sd").withLogo("logo");
    app.group().modifyGroup(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());
    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before, after);

  }


}
