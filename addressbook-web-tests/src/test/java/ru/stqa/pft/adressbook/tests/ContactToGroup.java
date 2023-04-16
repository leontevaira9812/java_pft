package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactToGroup extends TestBase {
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
    if (app.contact().seachGroupWithContacts(groups) == null) {
      app.contact().selectContactById(app.db().contacts().iterator().next().getId());
      app.contact().selectGroupFromList(app.db().groups().iterator().next().name);
      app.contact().clickAddToGroup();
      app.goTo().returnToHomePage();
    }
  }

  @Test
  public void TectContactAddingToGroup() {
    Groups groups = app.db().groups();
    GroupData AddedGroup = groups.iterator().next();
    Contacts contactsBefore = app.db().contacts();
    ContactData contactWithoutGroup = contactsBefore.iterator().next();
    app.contact().selectContactById(contactWithoutGroup.getId());
    app.contact().selectGroupFromList(AddedGroup.name);
    app.contact().clickAddToGroup();
    app.goTo().returnToHomePage();
    Contacts contactsAfter = app.db().contacts();
    ContactData contactWithGroup = contactWithoutGroup;
    contactWithGroup = app.contact().findAddingContact(contactsAfter, contactWithoutGroup.getId());
    assertThat(contactWithGroup, equalTo(contactWithoutGroup.
            withAddedGroup(AddedGroup)));


  }

  @Test(enabled = false)
  public void TectContactDeleteFromGroup() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    GroupData groupWithContact = app.contact().seachGroupWithContacts(groups);
    app.contact().selectGroupFromUpperList(groupWithContact.name);
    ContactData removedContact = contacts.iterator().next();
    app.contact().selectContactById(contacts.iterator().next().getId());
    app.contact().removeContactFromGroup();
    app.goTo().returnToHomePage();
    Groups groupsAfter = app.db().groups();
    GroupData groupWithoutContact = groupWithContact;
    groupWithoutContact = app.contact().find(groupsAfter, groupWithContact.id);
    assertThat(groupWithoutContact, equalTo(groupWithContact.
            withoutAddedContact(removedContact)));


  }
}