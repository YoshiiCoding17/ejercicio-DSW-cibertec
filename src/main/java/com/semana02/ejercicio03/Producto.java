package com.semana02.ejercicio03;

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
public class Producto {

    private int idProducto;
    private String nombre;
    private double precio;
    private int stock;
    
}
