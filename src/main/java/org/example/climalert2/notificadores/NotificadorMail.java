package org.example.climalert2.notificadores;

import org.example.climalert2.dto.RegistroClimatico;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class NotificadorMail implements Notificador{

  @Value("${spring.mail.username}")
  private String mailUsername;

  private final JavaMailSender mailSender;

  public NotificadorMail(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public Boolean enviarAlerta(RegistroClimatico registro) {
    try {
      SimpleMailMessage mail = new SimpleMailMessage();

      mail.setFrom(mailUsername);
      mail.setTo("admin@clima.com", "emergencias@clima.com", "meteorologia@clima.com");
      mail.setSubject("ALERTA CLIMÁTICA CRÍTICA");

      String mensaje = String.format(
          "Se han detectado condiciones climáticas extremas en %s.\n\n" +
              "Detalle del clima:\n" +
              "- Temperatura: %.1f°C\n" +
              "- Humedad: %d%%\n" +
              "- Fecha y Hora: %s",
          registro.getUbicacion().getNombre(),
          registro.getDatos().getTemperatura(),
          registro.getDatos().getHumedad(),
          registro.getHoraDeMuestreo().toString()
      );

      mail.setText(mensaje);
      mailSender.send(mail);

      return true;

    } catch (MailException e) {
      System.out.println("Error enviando mail de alerta climática: " + e.getMessage());
      return false;
    }
  }
}
