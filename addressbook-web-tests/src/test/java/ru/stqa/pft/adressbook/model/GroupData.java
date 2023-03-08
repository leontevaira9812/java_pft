package ru.stqa.pft.adressbook.model;

import java.util.Objects;

public record GroupData(int id, String name, String logo, String comment) {


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return Objects.equals(id, groupData.id) && Objects.equals(name, groupData.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';

  }

  public int getId() {
    return id;
  }


}




/*
import java.util.Objects;

public class GroupData {
  private int id;
  private final String name;
  private final String logo;
  private final String comment;

  public GroupData(int id, String name, String logo, String comment) {
    this.id = id;
    this.name = name;
    this.logo = logo;
    this.comment = comment;

  }

  public String getLogo() {
    return logo;
  }

  public String getName() {
    return name;
  }

  public String getComment() {
    return comment;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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
    return Objects.equals(id, groupData.id) && Objects.equals(name, groupData.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

}

 */