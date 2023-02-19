package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;


public class ContactCreation extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initNewContact();
    app.getContactHelper().fillDataToContact(new ContactData("ira", "leon", "uly", "89876542354", "test@example.com"));
    app.getContactHelper().saveContact();
    app.getNavigationHelper().returnToHomePage();
    app.getSessionHelper().logout();
  }

}
