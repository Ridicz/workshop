package com.databases.workshop.backend.mechanic;

import com.databases.workshop.backend.model.Person;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Mechanics")
public class Mechanic extends Person {

  private int salary;
  private int age;
  private String specialization;

  public Mechanic() {

  }

  public Mechanic(String firstName, String lastName) {
    super(firstName, lastName);
  }

  public Mechanic(Integer id, String firstName, String lastName) {
    super(id, firstName, lastName);
  }

  public Mechanic(Integer id, String firstName, String lastName, int salary, int age, String specialization) {
    this(id, firstName, lastName);
    this.salary = salary;
    this.age = age;
    this.specialization = specialization;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getSpecialization() {
    return specialization;
  }

  public void setSpecialization(String specialization) {
    this.specialization = specialization;
  }
}
