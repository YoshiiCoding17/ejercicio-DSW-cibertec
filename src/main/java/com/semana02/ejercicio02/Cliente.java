package com.semana02.ejercicio02;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cliente {
    private int idCliente;
    private String nombre;
    private String apellido;
    private String dni;
}
