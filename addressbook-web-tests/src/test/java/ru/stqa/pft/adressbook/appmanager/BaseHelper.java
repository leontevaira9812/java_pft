package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseHelper {
  protected WebDriver driver;

  public BaseHelper(WebDriver driver) {
    this.driver = driver;
  }

  protected void click(By locator) {
    driver.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    driver.findElement(locator).clear();
    driver.findElement(locator).sendKeys(text);
  }
}
