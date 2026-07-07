package org.example.climalert2.services;

import org.example.climalert2.dto.RegistroClimatico;
import org.example.climalert2.notificadores.Notificador;
import org.example.climalert2.repositories.ClimaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AlertaService {

  private final ClimaRepository climaRepository;
  private final Notificador medio;

  public AlertaService(ClimaRepository climaRepository, Notificador medio) {
    this.climaRepository = climaRepository;
    this.medio = medio;
  }


  @Scheduled(fixedRate = 60000)
  //@Scheduled(fixedRate = 10000) test
  public void analizarCondicionesYNotificar() {
    RegistroClimatico ultimoRegistro = climaRepository.obtenerUltimoRegistro();

    if (ultimoRegistro != null) {
      double temp = ultimoRegistro.getDatos().getTemperatura();
      int humedad = ultimoRegistro.getDatos().getHumedad();
      // if(temp > 0){ test
      if (temp > 35.0 && humedad > 60) {
        System.out.println("ALERTA DISPARADA. Evaluando métricas...");
        medio.enviarAlerta(ultimoRegistro);
      } else {
        System.out.println("Clima estable. Temp: " + temp + "°C, Hum: " + humedad + "%");
      }
    } else {
      System.out.println("Esperando el primer registro climático para evaluar...");
    }
  }
}
