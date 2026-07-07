package org.example.climalert2.repositories;

import org.example.climalert2.dto.RegistroClimatico;

import java.util.List;

public interface ClimaRepository {

  void guardarRegistro(RegistroClimatico registro);

  RegistroClimatico obtenerUltimoRegistro();

  List<RegistroClimatico> obtenerHistorialCompleto();
}
