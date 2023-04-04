package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.adressbook.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {

  // public static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
  // public static final ApplicationManager app;
  public static final ApplicationManager app;

  static {
    try {
      app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  protected WebDriver driver;

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }


  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

}
