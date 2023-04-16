package ru.stqa.pft.adressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;

import java.util.List;

public class AddContactToGroup extends TestBase {
  private SessionFactory sessionFactory;

  @BeforeClass
  protected void setUpTest() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    } catch (Exception e) {
      e.printStackTrace();
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }

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

  @Test
  public void TectContactAddingToGroup() {
    Groups groups = app.db().groups();
    int idGroup = groups.iterator().next().getId();
    //  app.contact().selectGroupInDropDown(idGroup);
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
    for (ContactData contact : result) {
      System.out.println(contact);
      System.out.println("before test  " + contact.getGroups());
    }

    session.getTransaction().commit();
    session.close();
    Contacts contacts = app.db().contacts();

    app.contact().selectContactById(contacts.iterator().next().getId());
    app.contact().clickAddToGroup();
    app.goTo().returnToHomePage();
    Session session2 = sessionFactory.openSession();
    session2.beginTransaction();
    List<ContactData> result2 = session2.createQuery("from ContactData where deprecated = '0000-00-00'").list();
    for (ContactData contact : result2) {
      System.out.println(contact);
      System.out.println("after test contact 1 = " + contact.getGroups());
    }
  }
}
