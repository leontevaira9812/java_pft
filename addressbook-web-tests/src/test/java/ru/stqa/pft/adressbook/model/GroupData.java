package ru.stqa.pft.adressbook.model;


import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.Objects;

@XStreamAlias("group")
public class GroupData {
  @XStreamOmitField
  public int id = Integer.MAX_VALUE;
  @Expose
  public String name;
  @Expose
  public String logo;
  @Expose
  public String comment;
  public String ira;


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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return id == groupData.id && Objects.equals(name, groupData.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  public String logo() {
    return logo;
  }

  public String comment() {
    return comment;
  }
}

