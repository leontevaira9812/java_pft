package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends BaseHelper {

  public GroupHelper(WebDriver driver) {
    super(driver);
  }

  public void returnToGroupList() {
    click(By.linkText("groups"));
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

  public void selectGroup(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectGroupById(int id) {
    driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void editGroup() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public boolean checkIsGroupExist() {
    try {
      driver.findElement(By.className("group"));
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }

  }

  public int count() {
    return driver.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<GroupData>();
    List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData().withId(id).withName(name));
    }
    return groups;
  }

  private Groups groupCache = null;

  public Groups all() {
    if (groupCache != null) {
      return new Groups(groupCache);
    }
    groupCache = new Groups();
    List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      //  GroupData group = new GroupData(id, name, null, null);
      // GroupData group = new GroupData().withId(id).withName(name);
      groupCache.add(new GroupData().withId(id).withName(name));
    }
    return new Groups(groupCache);
  }

  public void create(GroupData group) {
    initNewGroup();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache = null;
    returnToGroupList();
  }

  public GroupData createGroup(GroupData group) {
    initNewGroup();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache = null;
    returnToGroupList();
    return group;
  }

  public void modify(GroupData group, int index) {
    selectGroup(index);
    editGroup();
    fillGroupForm(group);
    submitGroupModification();
    returnToGroupList();
  }

  public void delete(List<GroupData> before) {
    selectGroup(before.size() - 1);
    deleteGroup();
    returnToGroupList();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteGroup();
    groupCache = null;
    returnToGroupList();
  }

  public void modifyGroup(GroupData group) {
    selectGroupById(group.getId());
    editGroup();
    fillGroupForm(group);
    submitGroupModification();
    groupCache = null;
    returnToGroupList();
  }
}
