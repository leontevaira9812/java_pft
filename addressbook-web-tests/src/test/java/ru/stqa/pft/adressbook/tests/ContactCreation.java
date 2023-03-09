package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class ContactCreation extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().checkIsGroupExist(By.xpath("//span[contains(text(),'grname')]"))) {
      app.getGroupHelper().initNewGroup();
      app.getGroupHelper().fillGroupForm(new GroupData(0, "grname", "logname", "comm"));
      app.getGroupHelper().submitGroupCreation();
      app.getNavigationHelper().returnToHomePage();
    }
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initNewContact();
    ContactData contact = new ContactData(Integer.MAX_VALUE, "ira", "leon", "uly",
            "89876542354", "test@example.com", "grname");
    app.getContactHelper().fillDataToContact(contact, true);
    app.getContactHelper().saveContact();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    // app.getSessionHelper().logout();
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }

}
