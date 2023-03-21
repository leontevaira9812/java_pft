package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import java.util.Set;

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
    Set<ContactData> before = app.contact().all();
    ContactData modifiedGroup = before.iterator().next();
    ContactData contactModification = new ContactData(modifiedGroup.getId(), "Modificated", "test",
            "uly", "89876542354", "test@example.com", null);
    app.contact().modify(contactModification);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());
    before.remove(modifiedGroup);
    before.add(contactModification);
    //Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    //before.sort(byId);
    // after.sort(byId);
    Assert.assertEquals(before, after);
    //app.getSessionHelper().logout();

  }


}
