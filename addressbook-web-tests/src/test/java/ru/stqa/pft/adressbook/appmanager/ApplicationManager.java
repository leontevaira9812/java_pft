package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private final Properties properties;
  public WebDriver driver;
  private NavigationHelper navigationHelper;
  private SessionHelper sessionHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  public JavascriptExecutor js;
  private String browser;


  public ApplicationManager(String browser) throws IOException {

    this.browser = browser;
    properties = new Properties();

  }


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

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    if (browser.equals(BrowserType.FIREFOX)) {
      driver = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      driver = new ChromeDriver();
    }
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    js = (JavascriptExecutor) driver;
    groupHelper = new GroupHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper = new SessionHelper(driver);
    contactHelper = new ContactHelper(driver);
    //driver.get("http://localhost/addressbook/");
    driver.get(properties.getProperty("browserUrl"));
    sessionHelper.login(properties.getProperty("adminLogin"), properties.getProperty("adminPassword"));
  }

  public void stop() {
    driver.quit();
  }

  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }


  public ContactHelper contact() {
    return contactHelper;
  }

}
