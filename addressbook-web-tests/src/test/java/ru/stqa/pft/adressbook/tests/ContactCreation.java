package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;
import ru.stqa.pft.adressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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
    Contacts before = app.contact().all();
    // ContactData contact = new ContactData(Integer.MAX_VALUE, "ira", "leon", "uly",
    // "111", "222", "333", "test@example.com", "grname");
    ContactData contact = new ContactData().withName("ira").withLastname("leon").withAddress("uly").withFirstEmail("email1")
            .withSecondEmail("email2").withMobilePhone("111").withHomePhone("222").withWorkPhone("333").withGroup("grname");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(before.
            withAdded(contact.id(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
