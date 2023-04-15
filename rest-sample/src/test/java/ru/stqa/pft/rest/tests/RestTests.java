package ru.stqa.pft.rest.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

  @Test
  public void testCreateIssue() throws IOException {
    int issueIdforTest = 74;
    try {
      skipIfNotFixed(issueIdforTest);
    } catch (SkipException e) {
      System.out.println(e.getMessage());
    }
    Set<Issue> oldIssues = app.rest().getIssues();
    Issue newIssue = new Issue().withSubject("new_issueII").withDescription("new_is_decrI");
    int issueId = app.rest().createIssue(newIssue);
    Set<Issue> newIssues = app.rest().getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);

  }


}
