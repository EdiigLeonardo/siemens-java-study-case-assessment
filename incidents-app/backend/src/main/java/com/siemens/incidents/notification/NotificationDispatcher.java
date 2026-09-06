package com.siemens.incidents.notification;
import com.siemens.incidents.command.CreateIncidentHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotificationDispatcher {

  // BUG: 2 beans implementam NotificationStrategy (Email/Sms), injecao de UM
  // so -> NoUniqueBeanDefinitionException no arranque. Devia ser List<NotificationStrategy>.
  private final NotificationStrategy strategy;

  // BUG (dependencia circular): Dispatcher precisa do Handler "para auditoria",
  // e o Handler (ver command/) precisa do Dispatcher -> BeanCurrentlyInCreationException.
  private final CreateIncidentHandler handler;

  @Value("${notification.sender-name}") // BUG: chave no yml e "sender_name".
  private String senderName;

  public NotificationDispatcher(NotificationStrategy strategy, CreateIncidentHandler handler) {
    this.strategy = strategy;
    this.handler = handler;
  }

  public void notifyCreated(String title) {
    strategy.send(senderName + ": novo incidente - " + title);
  }
}
