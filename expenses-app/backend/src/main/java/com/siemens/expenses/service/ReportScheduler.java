package com.siemens.expenses.service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReportScheduler {
  // BUG: o TaskScheduler default do Spring Boot tem 1 thread. Esta tarefa
  // "pesada" (simulada aqui) bloqueia essa unica thread - nenhuma outra
  // @Scheduled da app corre enquanto isto nao terminar.
  @Scheduled(fixedDelay = 60000)
  public void generateMonthlyReport() throws InterruptedException {
    Thread.sleep(30000);
  }

  @Scheduled(fixedDelay = 5000)
  public void heartbeat() { System.out.println("heartbeat"); } // fica preso atras do metodo de cima
}
