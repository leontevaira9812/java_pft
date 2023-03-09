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
    return Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, lastname);
  }
}