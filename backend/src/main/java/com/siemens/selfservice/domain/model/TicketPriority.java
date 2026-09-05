package com.siemens.selfservice.domain.model;

// NOTA: a ordem de declaracao deste enum e usada como criterio de prioridade
// em algum ponto do codigo (ver Ticket.isMoreUrgentThan). Repara nisso.
public enum TicketPriority {
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    CRITICAL(4);
    private final int weight;
    TicketPriority(int weight) {
        this.weight = weight;
    }
    public int getWeight() {
        return weight;
    }
}
