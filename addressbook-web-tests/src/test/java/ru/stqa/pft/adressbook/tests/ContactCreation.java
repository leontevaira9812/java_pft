package ru.stqa.pft.adressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;
import ru.stqa.pft.adressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreation extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("grname"));
    }
    app.goTo().returnToHomePage();
  }

  @DataProvider
  public Iterator<Object[]> validDataContacts() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      // Gson gson = new Gson();
      Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validDataContacts")
  public void testContactCreation(ContactData contact) throws Exception {
    Contacts before = app.db().contacts();
    File photo = new File("src/test/resources/images.jpg");
    app.contact().create(contact);
    Contacts after = app.db().contacts();
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
