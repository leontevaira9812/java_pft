package ru.stqa.pft.adressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;


@Test
public class GroupModification extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("grname").withLogo("logo").withComment("comm"));
    }
  /*  if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("grname").withLogo("logo").withComment("comm"));
    }*/
  }

  public void testGroupModification() throws Exception {
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    //GroupData group = new GroupData(before.get(before.size() - 1).getId(), "grnameModificatio1n", "logname", "comm");
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("grnameModificatio1n23").
            withComment("sd").withLogo("logo");
    app.goTo().groupPage();
    app.group().modifyGroup(group);
    Groups after = app.db().groups();
    Assert.assertEquals(after.size(), before.size());
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withoutAdded(modifiedGroup).withAdded(group)));

  }


}
