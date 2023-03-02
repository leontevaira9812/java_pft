package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.adressbook.model.ContactData;

import java.util.List;

public class ContactHelper extends BaseHelper {


  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void fillDataToContact(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.name());
    type(By.name("lastname"), contactData.lastname());
    type(By.name("address"), contactData.city());
    type(By.name("mobile"), contactData.telephone());
    type(By.name("email"), contactData.email());
    if (creation) {
      if (isGroupDropDownEmpty()) {
        new Select(driver.findElement(By.name("new_group"))).selectByVisibleText("[none]");
      } else {

        WebElement select = driver.findElement(By.name("new_group"));
        Select dropDown = new Select(select);
        List<WebElement> Options = dropDown.getOptions();
        for (WebElement option : Options) {
          String name;
          name = option.getText();
          if (option.getText().equals(contactData.group())) {
            option.click();
          }
        }
       
        new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.group());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }


  public void initNewContact() {
    click(By.linkText("add new"));
  }

  public void saveContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void selectContact() {
    if (isElementPresent(By.className("odd")) || isElementPresent(By.className(""))) {
      click(By.name("selected[]"));
    } //else {
    // initNewContact();
    //fillDataToContact(new ContactData("ira", "leon", "uly",
    //     "89876542354", "test@example.com", "grname"), true);
    // saveContact();

  }


  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void closeAlert() {
    driver.switchTo().alert().accept();
  }

  public void clickEditIcon() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }
}
