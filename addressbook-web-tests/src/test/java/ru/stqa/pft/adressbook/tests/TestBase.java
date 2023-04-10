package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.adressbook.appmanager.ApplicationManager;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;

import java.io.IOException;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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

  public void verifyGroupsFromUi() {
    if (Boolean.getBoolean("verifyUi")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();

      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.name())).collect(Collectors.toSet())));


    }
  }


}
