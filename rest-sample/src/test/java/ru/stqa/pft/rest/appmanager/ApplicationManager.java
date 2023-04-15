package ru.stqa.pft.rest.appmanager;

import org.openqa.selenium.WebDriver;
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
  private RestHelper restHelper;
  private String browser;

  private WebDriver driver;

  public ApplicationManager(String browser) throws IOException {

    this.browser = browser;
    properties = new Properties();

  }


  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

  }

  public RestHelper rest() {
    if (restHelper == null) {
      restHelper = new RestHelper(this);
    }
    return restHelper;
  }

  public void stop() {
    if (driver != null) {
      driver.quit();
    }
  }


  public String getProperty(String key) {
    return properties.getProperty(key);

  }


  public WebDriver getDriver() {
    if (driver == null) {
      if (browser.equals(BrowserType.FIREFOX)) {
        driver = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
        driver = new ChromeDriver();
      }
      driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }
    return driver;
  }
}
