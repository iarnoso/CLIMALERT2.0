package org.example.climalert2.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosClima {
  @JsonProperty("temp_c")
  private double temperatura; //celsius

  @JsonProperty("humidity")
  private int humedad;

}
