package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import java.util.List;

public class ContactDelete extends TestBase {
  @Test
  public void testContactDelete() {
    ContactData contactCreation = new ContactData(0, "ira", "leon", "uly",
            "89876542354", "test@example.com", "grname");
    if (!app.isElementPresent(By.name("entry"))) {
      app.getContactHelper().initNewContact();
      app.getContactHelper().fillDataToContact(contactCreation, true);
      app.getContactHelper().saveContact();
      app.getNavigationHelper().returnToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().closeAlert();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);

  }
}
