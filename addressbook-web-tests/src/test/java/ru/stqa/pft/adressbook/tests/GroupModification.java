package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


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
    List<GroupData> before = app.group().list();
    //GroupData group = new GroupData(before.get(before.size() - 1).getId(), "grnameModificatio1n", "logname", "comm");
    GroupData group = new GroupData().withId(before.get(before.size() - 1).getId()).withName("grnameModificatio1n").
            withComment("sd").withLogo("logo");
    int index = before.size() - 1;
    app.group().modify(group, index);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    //Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }


}
