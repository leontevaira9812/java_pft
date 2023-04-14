package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.Users;
import ru.stqa.pft.mantis.model.UsersData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {
  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void TestChangePassword() throws IOException, MessagingException {
    String fio = "testfio";
    String password = "password";
    app.login().enterLoginCredentinals(app.getProperty("username"), app.getProperty("password"));
    app.users().manage();
    app.users().manageUsers();
    Users allUsers = app.db().users();
    UsersData selectedUser = allUsers.iterator().next();
    app.users().clickOnSelectedUser(selectedUser);
    app.users().clickOnResetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 20000);
    String email = selectedUser.getEmail();
    String user = selectedUser.getUsername();
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.users().changePassword(confirmationLink, fio, password);
    assertTrue(app.newSession().login(user, password));

  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }


  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
