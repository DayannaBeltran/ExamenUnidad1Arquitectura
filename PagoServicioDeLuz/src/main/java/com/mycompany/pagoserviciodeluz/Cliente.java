/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagoserviciodeluz;

/**
 *
 * @author daya
 */
public class Cliente {

    private String numeroServicio;
    private String nombre;
    private Consumo consumo;

    public Cliente(String numeroServicio, String nombre, Consumo consumo) {
        this.numeroServicio = numeroServicio;
        this.nombre = nombre;
        this.consumo = consumo;
    }

    // Revisamos si el numero de servicio coincide con la busqueda.
    public boolean coincideConNumeroServicio(String criterio) {
        return numeroServicio.startsWith(criterio);
    }

    // Obtenemos los datos del cliente.
    public String obtenerDatosCliente() {
        return nombre + " - " + numeroServicio;
    }

    public String getNumeroServicio() {
        return numeroServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public Consumo getConsumo() {
        return consumo;
    }

    // Mostramos los clientes en la lista.
    @Override
    public String toString() {
        return numeroServicio + " - " + nombre;
    }
}

