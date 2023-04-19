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


  @Test
  public void TectContactDeleteFromGroup2() {
    Groups groups = app.db().groups();
    if (app.contact().seachGroupWithContacts(groups) == null) {
      app.contact().selectContactById(app.db().contacts().iterator().next().getId());
      app.contact().selectGroupFromList(app.db().groups().iterator().next().id);
      app.contact().clickAddToGroup();
      app.goTo().returnToHomePage();
    }
    Contacts contactsBefore = app.db().contacts();
    ContactData contactWithGroup = app.contact().seachGroupInContacts(contactsBefore); // находим контакт в котором есть группа
    Groups contactGroupsBefore = contactWithGroup.getGroups(); // запоминаем какие группы были в контакте
    GroupData groupForDelete = contactWithGroup.getGroups().iterator().next();
    app.contact().selectGroupFromUpperList(groupForDelete.id); //выбираем любую группу контакта
    app.contact().selectContactById(contactWithGroup.getId());
    app.contact().removeContactFromGroup(); //удаляем группу из этого контакта
    app.goTo().returnToHomePage();
    Contacts contactsAfter = app.db().contacts();
    Groups contactGroupsAfter = app.contact().getContactGroupsAfter(contactWithGroup, contactsAfter);
    assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.withoutAdded(groupForDelete)));
  }

}