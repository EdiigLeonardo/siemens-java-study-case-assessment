package com.siemens.expenses.service;
import com.siemens.expenses.aspect.Auditable;
import com.siemens.expenses.model.Expense;
import com.siemens.expenses.model.ExpenseStatus;
import com.siemens.expenses.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

// BUG: classe "final" -> o Spring nao consegue criar subclasse CGLIB para
// fazer proxy (AOP/@Transactional). Fica sem aviso claro; os aspectos
// simplesmente nunca sao aplicados a este bean.
@Service
public final class ExpenseService {
  private final ExpenseRepository repository;
  public ExpenseService(ExpenseRepository repository) { this.repository = repository; }

  @Auditable
  public Expense create(String email, String description, double amount) {
    Expense e = new Expense();
    e.setEmployeeEmail(email); e.setDescription(description); e.setAmount(amount); e.setStatus(ExpenseStatus.PENDING);
    return repository.save(e);
  }

  public List<Expense> list() { return repository.findAll(); }
}
