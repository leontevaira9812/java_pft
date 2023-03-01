package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.adressbook.model.GroupData;

public class GroupHelper extends BaseHelper {

  public GroupHelper(WebDriver driver) {
    super(driver);
  }

  public void returnToGroupList() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.name());
    type(By.name("group_header"), groupData.logo());
    type(By.name("group_footer"), groupData.comment());
  }

  public void initNewGroup() {
    click(By.name("new"));
  }

  public void deleteGroup() {
    click(By.name("delete"));
  }

  public void selectGroup() {
    //isElementPresent(By.className("group"));
    click(By.name("selected[]"));
  }

  public void editGroup() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }
}
