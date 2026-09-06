package com.siemens.expenses.model;
import jakarta.persistence.*;

@Entity
public class Expense {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  private String employeeEmail;
  private String description;
  private double amount; // BUG: dinheiro em double -> erros de arredondamento acumulados.
  @Enumerated(EnumType.STRING) private ExpenseStatus status;

  public Long getId() { return id; }
  public String getEmployeeEmail() { return employeeEmail; }
  public void setEmployeeEmail(String v) { employeeEmail = v; }
  public String getDescription() { return description; }
  public void setDescription(String v) { description = v; }
  public double getAmount() { return amount; }
  public void setAmount(double v) { amount = v; }
  public ExpenseStatus getStatus() { return status; }
  public void setStatus(ExpenseStatus v) { status = v; }
}
