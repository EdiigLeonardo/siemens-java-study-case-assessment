package com.siemens.expenses.controller;
import com.siemens.expenses.model.Expense;
import com.siemens.expenses.service.ExpenseService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/expenses")
public class ExpenseController {
  private final ExpenseService service;
  public ExpenseController(ExpenseService service) { this.service = service; }

  @PostMapping
  public Expense create(@RequestParam String email, @RequestParam String description, @RequestParam double amount) {
    return service.create(email, description, amount);
  }
  @GetMapping public List<Expense> list() { return service.list(); }
}
