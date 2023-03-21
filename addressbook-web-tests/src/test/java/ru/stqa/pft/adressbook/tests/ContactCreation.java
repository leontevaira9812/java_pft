package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.GroupData;

import java.util.Set;


public class ContactCreation extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (!app.group().checkIsGroupExist(By.className("group"))) {
      //app.group().create(new GroupData(0, "grname", "logo", "comm"));
      app.group().create(new GroupData().withName("grname"));
    }
    app.goTo().returnToHomePage();
  }

  @Test
  public void testContactCreation() throws Exception {
    //List<ContactData> before = app.contact().list();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData(Integer.MAX_VALUE, "ira", "leon", "uly",
            "89876542354", "test@example.com", "grname");
    app.contact().create(contact);
    //List<ContactData> after = app.contact().list();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    contact.id(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    /*Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);*/
    Assert.assertEquals(after, before);
  }

}
