package com.siemens.leaveportal.exception;
public class LeaveRequestNotFoundException extends RuntimeException {
    public LeaveRequestNotFoundException(Long id) { super("Pedido nao encontrado: " + id); }
}
