package org.example.climalert2.notificadores;

import org.example.climalert2.dto.RegistroClimatico;

public interface Notificador {
  Boolean enviarAlerta(RegistroClimatico registro);
}
