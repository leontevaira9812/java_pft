package ru.stqa.pft.adressbook.model;

/*public record ContactData(int id, String name, String lastname, String city, String telephone, String email,
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


}

 */

import java.util.Objects;

public class ContactData {
  private int id;
  public String name;
  public String lastname;
  public String city;
  public String telephone;
  public String email;

  public String group;

  public ContactData(int id, String name, String lastname, String city, String telephone, String email, String group) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.telephone = telephone;
    this.email = email;
    this.city = city;
    this.group = group;

  }


  public String name() {
    return name;
  }

  public String lastname() {
    return lastname;
  }

  public String city() {
    return city;
  }

  public String telephone() {
    return telephone;
  }

  public String email() {
    return email;
  }

  public String group() {
    return group;
  }

  public ContactData id(int id) {
    this.id = id;
    return this;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public String getLastname() {
    return lastname;
  }

  public String getCity() {
    return city;
  }

  public String getTelephone() {
    return telephone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
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
    return Objects.hash(id, name);
  }


}