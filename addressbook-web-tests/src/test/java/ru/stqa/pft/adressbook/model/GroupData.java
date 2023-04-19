package ru.stqa.pft.adressbook.model;


import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@Entity
@Table(name = "group_list")
public class GroupData {
  @XStreamOmitField
  @Id
  @Column(name = "group_id")
  public int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "group_name")
  public String name;
  @Expose
  @Column(name = "group_header")
  @Type(type = "text")
  public String logo;
  @Expose
  @Column(name = "group_footer")
  @Type(type = "text")
  public String comment;
  @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
  private Set<ContactData> contacts = new HashSet<ContactData>();

  public Contacts getContacts() {
    return new Contacts(contacts);
  }

  public GroupData withName(String name) {
    this.name = name;
    return this;
  }

  public GroupData withComment(String comment) {
    this.comment = comment;
    return this;
  }


  public GroupData withLogo(String logo) {
    this.logo = logo;
    return this;

  }

  public GroupData withId(int id) {
    this.id = id;
    return this;
  }

  public int id() {
    return id;
  }

  public int getId() {
    return id;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return id == groupData.id && Objects.equals(name, groupData.name) && Objects.equals(logo, groupData.logo) && Objects.equals(comment, groupData.comment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, logo, comment);
  }

  public String name() {
    return name;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }

  public String logo() {
    return logo;
  }

  public String comment() {
    return comment;
  }

  public GroupData withoutAddedContact(ContactData contact) {
    contacts.remove(contact);
    return this;
  }
}

