package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.adressbook.model.ContactData;

public class ContactHelper extends BaseHelper {


  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void fillDataToContact(ContactData contactData) {
    type(By.name("firstname"), contactData.name());
    type(By.name("lastname"), contactData.lastname());
    type(By.name("address"), contactData.city());
    type(By.name("mobile"), contactData.telephone());
    type(By.name("email"), contactData.email());

  }

  public void initNewContact() {
    click(By.linkText("add new"));
  }

  public void saveContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }
}
