package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.GroupData;


public class ContactCreation extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().checkIsGroupExist(By.xpath("//span[contains(text(),'grname')]"))) {
      app.getGroupHelper().initNewGroup();
      app.getGroupHelper().fillGroupForm(new GroupData("grname", "logname", "comm"));
      app.getGroupHelper().submitGroupCreation();
      app.getNavigationHelper().returnToHomePage();
    }
    app.getContactHelper().initNewContact();
    app.getContactHelper().fillDataToContact(new ContactData("ira", "leon", "uly",
            "89876542354", "test@example.com", "grname"), true);
    app.getContactHelper().saveContact();
    app.getNavigationHelper().returnToHomePage();
    app.getSessionHelper().logout();

  }

}
