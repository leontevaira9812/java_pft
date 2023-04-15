package ru.stqa.pft.rest.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {

  public static final ApplicationManager app;

  static {
    try {
      app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();

  }


  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {

    app.stop();
  }


  boolean isIssueOpen(int issueId) throws IOException {
    if (app.rest().getIssuesStatus(issueId).equals("Open") || app.rest().getIssuesStatus(issueId).equals("In Progress")) {
      return true;
    }
    return false;

  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
