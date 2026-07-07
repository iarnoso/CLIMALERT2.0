package org.example.climalert2.services;

import org.example.climalert2.config.RestWeatherProperties;
import org.example.climalert2.dto.RegistroClimatico;import org.example.climalert2.repositories.ClimaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;import org.springframework.web.util.UriComponentsBuilder;import java.net.URI;
import java.time.LocalDateTime;


@Component
public class ClimaService {
  private final ClimaRepository climaRepository;
  private final RestTemplate restTemplate;
  private final RestWeatherProperties properties;


  public ClimaService(RestTemplate restTemplate, ClimaRepository repo, RestWeatherProperties properties) {
    this.restTemplate = restTemplate;
    this.climaRepository = repo;
    this.properties = properties;
  }

  public RegistroClimatico condicionActual(){
    URI uri  = UriComponentsBuilder.fromUriString(properties.getBaseUrl())
        .path("/current.json")
        .queryParam("key", properties.getKey())
        .queryParam("q", properties.getQ())
        .queryParam("current_fields", "temp_c, humidity")
        .build().
        toUri();
    RegistroClimatico registro = restTemplate.getForObject(uri, RegistroClimatico.class);
    if (registro != null) {
      registro.setHoraDeMuestreo(LocalDateTime.now());
    }
    return registro;
  }

  @Scheduled(fixedRate = 300000)
  //@Scheduled(fixedRate = 15000) test
  public void recolectarYGuardarClima() {
    RegistroClimatico climaDeHoy = condicionActual();

    if (climaDeHoy != null) {
      climaRepository.guardarRegistro(climaDeHoy);
      System.out.println("Datos climáticos guardados con éxito: "
          + climaDeHoy.getDatos().getTemperatura() + "°C, "
          + climaDeHoy.getDatos().getHumedad() + "% humedad.");
    } else {
      System.out.println("Hubo un error al obtener los datos de WeatherAPI.");
    }
  }
}
