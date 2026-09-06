package com.siemens.expenses.aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// BUG: sem @Order - a ordem relativa a outros @Aspect (ex.: seguranca) nao
// e garantida. Podes acabar a auditar dados que a aspecto de seguranca ainda ia bloquear.
@Aspect @Component
public class AuditAspect {
  @Around("@annotation(Auditable)")
  public Object audit(ProceedingJoinPoint pjp) throws Throwable {
    System.out.println("AUDIT: " + pjp.getSignature());
    return pjp.proceed();
  }
}
