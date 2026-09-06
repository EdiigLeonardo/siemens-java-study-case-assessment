package com.siemens.incidents.notification;
import org.springframework.stereotype.Service;
@Service
public class EmailNotificationStrategy implements NotificationStrategy {
  public void send(String message) { System.out.println("EMAIL: " + message); }
}
