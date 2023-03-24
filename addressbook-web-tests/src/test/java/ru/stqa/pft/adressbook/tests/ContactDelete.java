package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDelete extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    ContactData contactCreation = new ContactData().withName("ira").withLastname("leon").withAddress("uly").withMobilePhone("111")
            .withEmail("test@example.com").withGroup("grname");
    app.goTo().returnToHomePage();
    if (!app.isElementPresent(By.name("entry"))) {
      app.contact().create(contactCreation);
    }
  }

  @Test(enabled = true)
  public void testContactDelete() {
    Contacts before = app.contact().all();
    ContactData deletedGroup = before.iterator().next();
    app.contact().delete(deletedGroup);
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.withoutAdded(deletedGroup)));

  }


}
