package org.example.climalert2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroClimatico {
  private LocalDateTime horaDeMuestreo;

  @JsonProperty("location")
  private Ubicacion ubicacion;

  @JsonProperty("current")
  private DatosClima datos;

}
