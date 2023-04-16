package ru.stqa.pft.adressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")

public class ContactData {
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name = "firstname")
  public String name;
  @Expose
  @Column(name = "lastname")
  public String lastname;
  @Expose
  @Column(name = "address")
  @Type(type = "text")
  public String address;
  @Transient
  public String telephone;

  @Transient
  public String email;

  @Column(name = "home")
  @Type(type = "text")
  public String homePhone;
  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  public String mobilePhone;
  @Column(name = "work")
  @Type(type = "text")
  public String workPhone;
  @Expose
  @Column(name = "email")
  @Type(type = "text")
  public String firstEmail;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(name, that.name)
            && Objects.equals(lastname, that.lastname)
            && Objects.equals(address, that.address)
            && Objects.equals(mobilePhone, that.mobilePhone)
            && Objects.equals(firstEmail, that.firstEmail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastname, address, mobilePhone, firstEmail);
  }

  @Column(name = "email2")
  @Type(type = "text")
  public String secondEmail;
  @Column(name = "email3")
  @Type(type = "text")
  public String thirdEmail;
  @Transient
  public String allPhones;
  @Transient
  public String allEmails;
  @Column(name = "photo")
  @Type(type = "text")
  public String photo;

  public File photo() {
    return new File(photo);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }


  public String getAllPhones() {
    return allPhones;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String name() {
    return name;
  }

  public String lastname() {
    return lastname;
  }

  public String city() {
    return address;
  }

  public String email() {
    return email;
  }


  public String homePhone() {
    return homePhone;
  }

  public String mobilePhone() {
    return mobilePhone;
  }

  public String workPhone() {
    return workPhone;
  }

  public ContactData id(int id) {
    this.id = id;
    return this;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getFirstEmail() {
    return firstEmail;
  }

  public String getSecondEmail() {
    return secondEmail;
  }

  public String getThirdEmail() {
    return thirdEmail;
  }

  public int getId() {
    return id;
  }

  public String getAddress() {
    return address;
  }


  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withName(String name) {
    this.name = name;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withTelephone(String telephone) {
    this.telephone = telephone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withFirstEmail(String firstEmail) {
    this.firstEmail = firstEmail;
    return this;
  }

  public ContactData withSecondEmail(String secondEmail) {
    this.secondEmail = secondEmail;
    return this;
  }

  public ContactData withThirdEmail(String thirdEmail) {
    this.thirdEmail = thirdEmail;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            '}';
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }


  public String firstEmail() {
    return firstEmail;
  }

  public String secondEmail() {
    return secondEmail;
  }

  public String thirdEmail() {
    return thirdEmail;
  }

  public ContactData withAddedGroup(GroupData group) {
    groups.add(group);
    return this;
  }


}