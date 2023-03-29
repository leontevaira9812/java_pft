package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/group.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[]{new GroupData().withName(split[0]).withLogo(split[1]).withComment(split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    //GroupData group = new GroupData().withName("grname");
    app.group().create(group);
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    // group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("grname'").withLogo("logname").withComment("comment");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size()));
    // group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    assertThat(after, equalTo(before));
  }
}


