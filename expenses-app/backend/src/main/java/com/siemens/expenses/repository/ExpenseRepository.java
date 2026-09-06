package com.siemens.expenses.repository;
import com.siemens.expenses.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ExpenseRepository extends JpaRepository<Expense, Long> {}
