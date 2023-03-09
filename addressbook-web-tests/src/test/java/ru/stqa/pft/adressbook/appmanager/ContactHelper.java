package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.adressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {

  // protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

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
      new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.group());
    }
    // проверка на то существует ли переданная группа в выпадающем списке,
    // закоментировала потому что заменила это проверкой на существование группы на странице групс в начале теста
    //  if (!isGroupExistInContactCreationForm(contactData.group())) {
    //    new Select(driver.findElement(By.name("new_group"))).selectByVisibleText("[none]");
    //  } else {
    //    new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.group());
    //  }

    else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }
  // функция на проверку существует ли заданная группа в выпадающем списке
  /*private boolean isGroupExistInContactCreationForm(String group) {
    WebElement select = driver.findElement(By.name("new_group"));
    Select dropDown = new Select(select);
    List<WebElement> Options = dropDown.getOptions();
    for (WebElement option : Options) {
      String name;
      name = option.getText();
      if (option.getText().equals(group)) {
        return true;
      }
    }
    return false;
  }
*/

  public void initNewContact() {
    click(By.linkText("add new"));
  }

  public void saveContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void selectContact() {
    if (isElementPresent(By.className("odd")) || isElementPresent(By.name("entry"))) {
      click(By.name("selected[]"));
    } else {
      initNewContact();
      fillDataToContact(new ContactData(0, "ira", "leon", "uly",
              "89876542354", "test@example.com", "grname"), true);
      saveContact();
      click(By.linkText("home"));


      // app.getNavigationHelper().returnToHomePage();

    }

  }


  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void closeAlert() {
    driver.switchTo().alert().accept();
  }

  public void clickEditIcon(int index) {
   /* if (!isElementPresent(By.name("entry"))) {
      initNewContact();
      fillDataToContact(new ContactData(0, "ira", "leon", "uly",
              "89876542354", "test@example.com", "grname"), true);
      saveContact();
      click(By.linkText("home"));
    }
    */
    // click(By.xpath("//img[@alt='Edit']"));
    click(By.xpath("//*[@id='maintable']/tbody/tr[" + (index) + "]/td[8]/a"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }


  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> rows = driver.findElements(By.cssSelector("table tr"));
    for (int i = 1; i < rows.size(); i++) {
      // for (WebElement row : rows) {
      List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
      int id = Integer.parseInt(rows.get(i).findElement(By.tagName("input")).getAttribute("value"));
      String name = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      ContactData contact = new ContactData(id, name, lastname, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}