package org.example.climalert2.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rest-weather")
@Data
public class RestWeatherProperties {
  private String baseUrl;
  private String key;
  private String q;
}
