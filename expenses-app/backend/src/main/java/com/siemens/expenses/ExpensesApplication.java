package com.siemens.expenses;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling @SpringBootApplication
public class ExpensesApplication {
  public static void main(String[] a) { SpringApplication.run(ExpensesApplication.class, a); }
}
