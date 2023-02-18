package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ApplicationManager {
  public WebDriver driver;
  private NavigationHelper navigationHelper;
  private SessionHelper sessionHelper;
  private GroupHelper groupHelper;
  public JavascriptExecutor js;


  public boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void init() {
    System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    js = (JavascriptExecutor) driver;
    groupHelper = new GroupHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper = new SessionHelper(driver);
    driver.get("http://localhost/addressbook/");
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    driver.quit();
  }

  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
