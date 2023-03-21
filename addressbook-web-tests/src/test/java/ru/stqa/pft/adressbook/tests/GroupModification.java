package ru.stqa.pft.adressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;


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
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    //GroupData group = new GroupData(before.get(before.size() - 1).getId(), "grnameModificatio1n", "logname", "comm");
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("grnameModificatio1n23").
            withComment("sd").withLogo("logo");
    app.group().modifyGroup(group);
    Groups after = app.group().all();
    Assert.assertEquals(after.size(), before.size());
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withoutAdded(modifiedGroup).withAdded(group)));

  }


}
