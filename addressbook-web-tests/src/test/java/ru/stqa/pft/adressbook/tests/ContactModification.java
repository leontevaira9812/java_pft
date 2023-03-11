package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModification extends TestBase {
  @Test
  public void testContactModification() {
    ContactData contactCreation = new ContactData(0, "ira", "leon", "uly",
            "89876542354", "test@example.com", "grname");
    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().checkIsGroupExist(By.cssSelector("span.group"))) {
      app.getGroupHelper().initNewGroup();
      app.getGroupHelper().fillGroupForm(new GroupData(0, "grname", "lll", "comm"));
      app.getGroupHelper().submitGroupCreation();
    }
    app.getNavigationHelper().returnToHomePage();
    if (!app.isElementPresent(By.name("entry"))) {
      app.getContactHelper().initNewContact();
      app.getContactHelper().fillDataToContact(contactCreation, true);
      app.getContactHelper().saveContact();
      app.getNavigationHelper().returnToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().clickEditIcon(before.size() + 1);
    ContactData contactModification = new ContactData(before.get(before.size() - 1).getId(), "ModificatioTTEST", "aaaLeon",
            "uly", "89876542354", "test@example.com", null);
    app.getContactHelper().fillDataToContact(contactModification, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contactModification);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    //app.getSessionHelper().logout();

  }
}
