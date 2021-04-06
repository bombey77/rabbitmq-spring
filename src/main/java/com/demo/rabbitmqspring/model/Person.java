package com.demo.rabbitmqspring.model;

import java.io.Serializable;

public class Person implements Serializable {

  private String name;
  private String sureName;
  private Integer age;
  private String email;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getSureName() {
    return sureName;
  }

  public void setSureName(final String sureName) {
    this.sureName = sureName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(final Integer age) {
    this.age = age;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }
}
