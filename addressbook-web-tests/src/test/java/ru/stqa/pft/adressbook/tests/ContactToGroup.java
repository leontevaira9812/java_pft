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
    Groups groups = app.db().groups();
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("grname"));
      groups = app.db().groups();
    }
    app.goTo().returnToHomePage();
    ContactData contactCreation = new ContactData().withName("ira").withLastname("leon").withAddress("uly").withMobilePhone("111")
            .withEmail("test@example.com").withAddedGroup(groups.iterator().next());
    if (app.db().contacts().size() == 0) {
      app.contact().create(contactCreation);
    }
  }

  @Test
  public void TectContactAddingToGroup() {
    GroupData addedGroup = app.db().groups().iterator().next();
    ContactData contactWithoutGroup = app.db().contacts().iterator().next();
    Groups groupsBefore = app.db().groups();
    Contacts contactsBefore = app.db().contacts();
    boolean isFoundContactWithoutGroup = false;
    //проходимся по каждому контакту и по каждой группе и  смотрим есть ли такая группа
    // в которой контакт не состоит
    // если такая есть, то записываем эту группу в переменную addedGroup и записываем этот контакт в переменную contactWithoutGroup
    for (ContactData contact : contactsBefore) {
      if (isFoundContactWithoutGroup) break;
      for (GroupData group : groupsBefore) {
        if (!contact.getGroups().contains(group)) {
          addedGroup = group;
          contactWithoutGroup = contact;
          isFoundContactWithoutGroup = true; // контакт без группы найден, выходим из цикла
          break;
        }
      }
    }
    if (!isFoundContactWithoutGroup) { // если контакт без группы не найден (все контакты состоят во всех группах) ,
      // то создаем новую группу и выбираем любой контакт для добавления в эту группу
      app.goTo().groupPage();
      addedGroup = app.group().createGroup(new GroupData().withName("test2").withComment("comm").withLogo("logo"));
      contactWithoutGroup = contactsBefore.iterator().next();
      app.goTo().returnToHomePage();
      Groups groupsAfter = app.db().groups();
      addedGroup.withId(groupsAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    }
    app.contact().selectContactById(contactWithoutGroup.getId());
    app.contact().selectGroupFromList(addedGroup.id);
    app.contact().clickAddToGroup();
    app.goTo().returnToHomePage();
    Contacts contactsAfter = app.db().contacts();
    ContactData contactWithGroup = app.contact().findAddingContact(contactsAfter, contactWithoutGroup.getId());


    assertThat(contactWithGroup, equalTo(contactWithoutGroup.withAddedGroup(addedGroup)));
  }


  @Test(enabled = false)
  public void TectContactDeleteFromGroup() {
    Groups groups = app.db().groups();
    if (app.contact().seachGroupWithContacts(groups) == null) {
      app.contact().selectContactById(app.db().contacts().iterator().next().getId());
      app.contact().selectGroupFromList(app.db().groups().iterator().next().id);
      app.contact().clickAddToGroup();
      app.goTo().returnToHomePage();
    }
    Groups groupsBefore = app.db().groups();
    GroupData groupWithContact = app.contact().seachGroupWithContacts(groupsBefore);
    app.contact().selectGroupFromUpperList(groupWithContact.id);
    ContactData removedContact = groupWithContact.getContacts().iterator().next();
    app.contact().selectContactById(removedContact.getId());
    app.contact().removeContactFromGroup();
    app.goTo().returnToHomePage();
    Groups groupsAfter = app.db().groups();
    GroupData groupWithoutContact = app.contact().find(groupsAfter, groupWithContact.id);
    assertThat(groupWithoutContact.getContacts(), equalTo(groupWithContact.
            withoutAddedContact(removedContact).getContacts().toArray()));


  }

  @Test(enabled = false)
  public void TectContactDeleteFromGroup1() {
    Groups groupsBefore = app.db().groups();
    Contacts contactsBefore = app.db().contacts();
    GroupData groupWithContact = app.contact().seachGroupWithContacts(groupsBefore); // находим группу в которой есть контакт
    app.contact().selectGroupFromUpperList(groupWithContact.id); //выбираем эту группу в списке на вебе
    ContactData contactWithGroup = groupWithContact.getContacts().iterator().next(); // выбираем любой контакт из этой группы на удаление
    app.contact().selectContactById(contactWithGroup.getId()); //выбираем контакт на вебе
    app.contact().removeContactFromGroup();
    app.goTo().returnToHomePage();
    Groups groupsAfter = app.db().groups();
    Contacts contactsAfter = app.db().contacts();
    ContactData contactWithoutGroup = app.contact().findAddingContact(contactsAfter, contactWithGroup.getId());
    //GroupData groupWithoutContact = app.contact().find(groupsAfter, groupWithContact.id);
    assertThat(contactWithoutGroup, equalTo(contactWithGroup.
            withoutGroup(groupWithContact)));
  }
}