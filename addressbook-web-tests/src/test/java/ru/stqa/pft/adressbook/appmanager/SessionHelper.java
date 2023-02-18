package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends BaseHelper {
  public SessionHelper(WebDriver driver) {
    super(driver);
  }

  public void login(String username, String pass) {
    type(By.name("user"), username);
    type(By.name("pass"), pass);
    click(By.xpath("//input[@value='Login']"));

  }

  public void logout() {
    click(By.linkText("Logout"));
  }
}
