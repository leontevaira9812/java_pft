package ru.stqa.pft.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

public class RestHelper {

  private ApplicationManager app;

  public RestHelper(ApplicationManager applicationManager) {
    this.app = applicationManager;
  }

  public Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get(app.getProperty("bg.urlIssues") + "?pageSize=500")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson()
            .fromJson(issues, new TypeToken<Set<Issue>>() {
            }.getType());
  }


  public String getIssuesStatus(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get(String.format("https://bugify.stqa.ru/api/issues/%s.json", issueId)))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0);

    String status = issues.getAsJsonObject().get("state_name").getAsString();
    return status;
  }

  public int createIssue(Issue newIssue) throws IOException {
    String json = getExecutor().execute(Request
                    .Post(app.getProperty("bg.urlIssues"))
                    .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                            new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();

  }


  private Executor getExecutor() {
    return Executor.newInstance().auth("b31e382ca8445202e66b03aaf31508a3", "");
  }

}
