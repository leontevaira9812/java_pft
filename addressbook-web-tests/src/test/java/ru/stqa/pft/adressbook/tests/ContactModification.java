package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

public class ContactModification extends TestBase {
  @Test
  public void testContactModification() {
    app.getContactHelper().clickEditIcon();
    app.getContactHelper().fillDataToContact(new ContactData("Modification", "Leon",
            "uly", "89876542354", "test@example.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    app.getSessionHelper().logout();

  }
}
