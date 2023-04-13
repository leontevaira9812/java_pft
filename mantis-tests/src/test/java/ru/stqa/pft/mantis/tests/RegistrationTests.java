package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

  @Test
  public void TestRegistration() {
    app.registration().start("user1", "user1@loclhost.localdomain");


  }
}
