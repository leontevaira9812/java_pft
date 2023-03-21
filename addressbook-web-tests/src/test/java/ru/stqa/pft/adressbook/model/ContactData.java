package ru.stqa.pft.adressbook.model;

import java.util.Objects;

public record ContactData(int id, String name, String lastname, String city, String telephone, String email,
                          String group) {
  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastname);
  }

  @Override
  public int id() {
    return id;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public String lastname() {
    return lastname;
  }

  @Override
  public String city() {
    return city;
  }

  @Override
  public String telephone() {
    return telephone;
  }

  @Override
  public String email() {
    return email;
  }

  @Override
  public String group() {
    return group;
  }


  public void id(int asInt) {
  }
}