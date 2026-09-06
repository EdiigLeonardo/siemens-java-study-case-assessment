package com.siemens.expenses.aspect;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
// BUG: falta @Retention(RetentionPolicy.RUNTIME). Sem isso a JVM descarta a
// anotacao depois da compilacao - o Aspect nunca a ve via reflection, o
// pointcut "@annotation(Auditable)" nunca casa com nada.
@Target(ElementType.METHOD)
public @interface Auditable {}
