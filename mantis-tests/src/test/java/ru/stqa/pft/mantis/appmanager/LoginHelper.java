package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends BaseHelper {
  public LoginHelper(ApplicationManager app) {
    super(app);
  }

  public void enterLoginCredentinals(String username, String password) {
    //driver.get("http://localhost/mantisbt-1.3.20//login_page.php");

    driver.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Войти']"));
  }

}
