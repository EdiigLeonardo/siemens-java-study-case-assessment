package com.siemens.leaveportal.exception;

// BUG: excecao CHECKED (extends Exception, nao RuntimeException).
// Por default, o @Transactional do Spring so faz rollback em
// RuntimeException/Error. Uma excecao checked lancada dentro de um metodo
// @Transactional NAO desfaz a transacao - o estado fica inconsistente.
public class InsufficientLeaveBalanceException extends Exception {
    public InsufficientLeaveBalanceException(String message) { super(message); }
}
