package com.siemens.expenses.service;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class TaxCalculator {
  // BUG: divide sem RoundingMode -> ArithmeticException se o resultado nao
  // for uma decimal exata (ex.: dividir por 3).
  public BigDecimal splitEvenly(BigDecimal amount, int parts) {
    return amount.divide(BigDecimal.valueOf(parts));
  }

  // BUG: compara com equals() - "0.0" (scale 1) != "0" (scale 0) mesmo
  // representando o mesmo valor. Devia usar compareTo(...) == 0.
  public boolean isZero(BigDecimal amount) {
    return amount.equals(BigDecimal.ZERO);
  }
}
