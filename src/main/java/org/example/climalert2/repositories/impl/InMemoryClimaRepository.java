package org.example.climalert2.repositories.impl;



import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.example.climalert2.dto.RegistroClimatico;
import org.example.climalert2.repositories.ClimaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryClimaRepository implements ClimaRepository {
  private final List<RegistroClimatico> historial = new CopyOnWriteArrayList<>();

  @Override
  public void guardarRegistro(RegistroClimatico registro) {
    historial.add(registro);
  }

  @Override
  public RegistroClimatico obtenerUltimoRegistro() {
    if (historial.isEmpty()) {
      return null;
    }
    return historial.getLast();
  }

  @Override
  public List<RegistroClimatico> obtenerHistorialCompleto() {
    return historial;
  }
}
