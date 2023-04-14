package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "mantis_user_table")
public class UsersData {

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "username")
  private String username;

  @Column(name = "email")
  private String email;


  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }


  public UsersData setUsername(String username) {
    this.username = username;
    return this;
  }

  public UsersData setEmail(String email) {
    this.email = email;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UsersData usersData = (UsersData) o;

    if (!Objects.equals(username, usersData.username)) return false;
    return Objects.equals(email, usersData.email);
  }

  @Override
  public int hashCode() {
    int result = username != null ? username.hashCode() : 0;
    result = 31 * result + (email != null ? email.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "UsersData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
