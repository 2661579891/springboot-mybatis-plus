package com.mp.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user")
public class User {
  @TableId
  private long id;
  private long userAge;
  private String userName;
  private String userPassword;
  private String userRealName;
  private String userEmail;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getUserAge() {
    return userAge;
  }

  public void setUserAge(long userAge) {
    this.userAge = userAge;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  public String getUserRealName() {
    return userRealName;
  }

  public void setUserRealName(String userRealName) {
    this.userRealName = userRealName;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", userAge=" + userAge +
            ", userName='" + userName + '\'' +
            ", userPassword='" + userPassword + '\'' +
            ", userRealName='" + userRealName + '\'' +
            ", userEmail='" + userEmail + '\'' +
            '}';
  }
}
