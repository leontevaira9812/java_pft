package ru.stqa.pft.mantis.appmanager;

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
  private RegistrationHelper registrationHelper;
  private UsersHelper users;

  private LoginHelper loginHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private SoapHelper soapHelper;
  private String browser;
  private DbHelper db;

  private WebDriver driver;

  public ApplicationManager(String browser) throws IOException {

    this.browser = browser;
    properties = new Properties();

  }


  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

  }

  public void stop() {
    if (driver != null) {
      driver.quit();
    }
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);

  }


  public LoginHelper login() {
    if (loginHelper == null) {
      loginHelper = new LoginHelper(this);
    }
    return loginHelper;
  }

  public UsersHelper users() {
    if (users == null) {
      users = new UsersHelper(this);
    }
    return users;
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public FtpHelper ftp() {
    if (ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  public DbHelper db() {
    if (db == null) {
      db = new DbHelper(this);
    }
    return db;
  }

  public MailHelper mail() {
    if (mailHelper == null) {
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public SoapHelper soap() {
    if (soapHelper == null) {
      soapHelper = new SoapHelper(this);
    }
    return soapHelper;
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
