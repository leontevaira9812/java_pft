package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;

import java.util.List;

import static ru.stqa.pft.adressbook.tests.TestBase.app;

public class ContactHelper extends BaseHelper {


  // protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

  public ContactHelper(WebDriver driver) {
    super(driver);

  }

  public void fillDataToContact(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.name());
    type(By.name("lastname"), contactData.lastname());
    //attach(By.name("photo"), contactData.photo());
    type(By.name("address"), contactData.city());
    type(By.name("home"), contactData.homePhone());
    type(By.name("mobile"), contactData.mobilePhone());
    type(By.name("work"), contactData.workPhone());
    type(By.name("email"), contactData.firstEmail());
    type(By.name("email2"), contactData.secondEmail());
    type(By.name("email3"), contactData.thirdEmail());
    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().name());
      }
    }

   /* if (creation) {
      try {
        new Select(driver.findElement(By.name("new_group")))
                .selectByVisibleText(contactData.group());
      } catch (NullPointerException e) {
        new Select(driver.findElement(By.name("new_group"))).selectByVisibleText("[none]");
      }

      //  new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.group());
    }*/
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

  public void selectContact(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();
    /*if (isElementPresent(By.className("odd")) || isElementPresent(By.name("entry"))) {
      click(By.name("selected[]"));
    } else {
      initNewContact();
      fillDataToContact(new ContactData(0, "ira", "leon", "uly",
              "89876542354", "test@example.com", "grname"), true);
      saveContact();
      click(By.linkText("home"));
    } */

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
    // click(By.xpath("//*[@id='maintable']/tbody/tr[" + (index) + "]/td[8]/a"));
    //  driver.findElement(By.cssSelector("input[value='" + index + "']")).findElements(By.xpath("//*[@id='maintable']/tbody/tr[" + (index) + "]/td[8]/a"));
    String url = "edit.php?id=";
    driver.findElement(By.xpath("//a[@href=\"" + url + index + "\"]")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }


  /* public List<ContactData> list() {
     List<ContactData> contacts = new ArrayList<ContactData>();
     List<WebElement> rows = driver.findElements(By.cssSelector("table tr"));
     for (int i = 1; i < rows.size(); i++) {
       // for (WebElement row : rows) {
       List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
       int id = Integer.parseInt(rows.get(i).findElement(By.tagName("input")).getAttribute("value"));
       String name = cells.get(2).getText();
       String lastname = cells.get(1).getText();
       ContactData contact = new ContactData(id, name, lastname, null, null, null, null, null);
       contacts.add(contact);
     }
     return contacts;
   }
 */
  private Contacts contactCache = null;

  public Contacts all() {
  /*  if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts(); */
    Contacts contacts = new Contacts();
    List<WebElement> rows = driver.findElements(By.cssSelector("table tr"));
    for (int i = 1; i < rows.size(); i++) {
      // for (WebElement row : rows) {
      List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
      int id = Integer.parseInt(rows.get(i).findElement(By.tagName("input")).getAttribute("value"));
      String name = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String address = cells.get(3).getText();
      // String[] emails = cells.get(4).getText().split("\n");
      //  String[] phones = cells.get(5).getText().split("\n");
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();

      ContactData contact = new ContactData().withId(id).withName(name)
              .withLastname(lastname).withAddress(address).withAllPhones(allPhones).withAllEmails(allEmails);
      contacts.add(contact);
    }
    return contacts;
  }

  public void create(ContactData contactCreation) {
    initNewContact();
    fillDataToContact(contactCreation, true);
    saveContact();
    app.goTo().returnToHomePage();
  }

  public void modify(ContactData contactModification) {
    clickEditIcon(contactModification.getId());
    fillDataToContact(contactModification, false);
    submitContactModification();
    // contactCache = null;
    app.goTo().returnToHomePage();
  }

  public void delete(List<ContactData> before) {
    selectContact(before.size() - 1);
    deleteContact();
    closeAlert();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    //contactCache = null;
    closeAlert();
  }

  public void selectContactById(int id) {
    driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
    String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
    String city = driver.findElement(By.name("address")).getAttribute("value");
    String home = driver.findElement(By.name("home")).getAttribute("value");
    String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
    String work = driver.findElement(By.name("work")).getAttribute("value");
    String firstEmail = driver.findElement(By.name("email")).getAttribute("value");
    String secondEmail = driver.findElement(By.name("email2")).getAttribute("value");
    String thirdEmail = driver.findElement(By.name("email3")).getAttribute("value");
    driver.navigate().back();
    return new ContactData().withId(contact.getId()).withName(firstname).withLastname(lastname)
            .withAddress(city).withMobilePhone(mobile).withHomePhone(home).withWorkPhone(work)
            .withFirstEmail(firstEmail).withSecondEmail(secondEmail).withThirdEmail(thirdEmail);
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = driver.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("../../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

    //driver.findElement(By.xpath(String.format("input[@value='s%']/../../td[8]/a", id))).click();
    // driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=s%']",id))).click();
  }

  public boolean isContactExists() {
    if (!app.isElementPresent(By.name("entry"))) {
      return false;
    }
    return true;
  }

  public void selectGroupInDropDown(int idGroup) {
    click(By.xpath(String.format("xpath=//input[@id='%s']", idGroup)));


  }

  public void clickAddToGroup() {
    click(By.name("add"));
  }
}