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
}
