package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;
import ru.stqa.pft.adressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreation extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (!app.group().checkIsGroupExist()) {
      app.group().create(new GroupData().withName("grname"));
    }
    app.goTo().returnToHomePage();
  }

  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/images.jpg");
    ContactData contact = new ContactData().withName("ira").withLastname("leon").withPhoto(photo).withAddress("uly").withFirstEmail("email1")
            .withSecondEmail("email2").withMobilePhone("111").withHomePhone("222").withWorkPhone("333").withGroup("grname");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(before.
            withAdded(contact.id(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void photoTest() {
    File curPhoto = new File(".");
    System.out.println(curPhoto.getAbsoluteFile());
    File photo = new File("src/test/resources/images.jpg");
    System.out.println(photo.exists());
    System.out.println(photo.getAbsoluteFile());
  }
}
