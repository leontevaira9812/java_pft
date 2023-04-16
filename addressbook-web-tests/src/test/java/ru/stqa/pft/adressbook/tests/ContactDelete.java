package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDelete extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("grname"));
    }
    Groups groups = app.db().groups();
    ContactData contactCreation = new ContactData().withName("ira").withLastname("leon").withAddress("uly").withMobilePhone("111")
            .withEmail("test@example.com").withAddedGroup(groups.iterator().next());
    app.goTo().returnToHomePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(contactCreation);
    }
  }

  @Test(enabled = true)
  public void testContactDelete() {
    Contacts before = app.db().contacts();
    ContactData contactDeleted = before.iterator().next();
    app.contact().delete(contactDeleted);
    Contacts after = app.db().contacts();
    Assert.assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.withoutAdded(contactDeleted)));

  }


}
