package com.siemens.expenses.aspect;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
@Aspect @Component
public class SecurityAspect {
  @Before("@annotation(Auditable)")
  public void check() { System.out.println("SECURITY CHECK"); } // devia correr ANTES do audit, ordem nao garantida
}
