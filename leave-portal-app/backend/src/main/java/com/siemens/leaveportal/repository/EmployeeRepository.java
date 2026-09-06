package com.siemens.leaveportal.repository;
import com.siemens.leaveportal.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
