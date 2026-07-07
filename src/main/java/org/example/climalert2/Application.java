package org.example.climalert2;

import org.example.climalert2.config.RestWeatherProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(RestWeatherProperties.class)
@EnableScheduling
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
