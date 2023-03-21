package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;

import java.util.Set;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    //List<GroupData> before = app.group().list();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("grname").withLogo("logname").withComment("comment");
    app.group().create(group);
    //List<GroupData> after = app.group().list();
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    /*int max = 0;
    for (GroupData g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    group.withId(max);
     */
    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    //Assert.assertEquals(new HashSet<>(after), new HashSet<>(before));
    Assert.assertEquals(before, after);
  }
}


