package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UsersData;

public class UsersHelper extends BaseHelper {
  public UsersHelper(ApplicationManager app) {
    super(app);
  }

  public void manage() {
    click(By.linkText("управление"));

  }

  public void manageUsers() {
    click(By.linkText("Управление пользователями"));
  }

  public void clickOnSelectedUser(UsersData selectedUser) {
    String user = selectedUser.getUsername();
    click(By.linkText(user));
  }

  public void clickOnResetPassword() {
    click(By.cssSelector("input[value='Сбросить пароль']\""));
  }
}
