package ru.stqa.pft.adressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;

public class ContactModification extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    ContactData contactCreation = new ContactData(0, "ira", "leon", "uly",
            "89876542354", "test@example.com", "grname");
   /* app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().checkIsGroupExist(By.className("group"))) {
      app.getGroupHelper().createGroup();
    }
    app.getNavigationHelper().returnToHomePage(); */
    if (!app.isElementPresent(By.name("entry"))) {
      app.contact().create(contactCreation);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contactModification = new ContactData(modifiedContact.getId(), "Modificated", "test",
            "uly", "89876542354", "test@example.com", null);
    app.contact().modify(contactModification);
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withoutAdded(modifiedContact).withAdded(contactModification)));

  }


}
