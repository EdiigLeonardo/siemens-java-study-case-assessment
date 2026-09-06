package com.siemens.incidents.dto;
import com.siemens.incidents.model.IncidentStatus;
import lombok.Data;

// BUG: mesmo DTO para pedido (create) e resposta (get/list) -> "internalNotes"
// acaba serializado e devolvido ao cliente sem querer.
@Data
public class IncidentDto {
  private Long id;
  private String title;
  private IncidentStatus status;
  private String internalNotes;
}
