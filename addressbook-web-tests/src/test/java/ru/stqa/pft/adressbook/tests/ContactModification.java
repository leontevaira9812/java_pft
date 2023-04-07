package ru.stqa.pft.adressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;

public class ContactModification extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    ContactData contactCreation = new ContactData().withName("ira").withLastname("leon").withAddress("uly").withMobilePhone("111")
            .withEmail("test@example.com").withGroup("grname");
    if (app.db().contacts().size() == 0) {
      app.contact().create(contactCreation);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contactModification = new ContactData().withId(modifiedContact.getId()).withName("Modificated").withLastname("test")
            .withAddress("uly").withMobilePhone("111").withHomePhone("222").withEmail("test@example.com");
    app.contact().modify(contactModification);
    Contacts after = app.db().contacts();
    Assert.assertEquals(after.size(), before.size());
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withoutAdded(modifiedContact).withAdded(contactModification)));

  }


}
