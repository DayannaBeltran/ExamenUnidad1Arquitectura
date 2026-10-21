/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagoserviciodeluz;
import java.util.List;
/**
 *
 * @author daya
 * 
 */
public class ControlPagoLuz {
     private PantallaPagoLuz vista;
    private ModeloNegocio modelo;

    public ControlPagoLuz(PantallaPagoLuz vista, ModeloNegocio modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    // Solicitamos al modelo los clientes coincidentes.
    public List<Cliente> buscarClientesCoincidentes(String criterio) {
        return modelo.buscarClientesCoincidentes(criterio);
    }

    // Seleccionamos al cliente.
    public Cliente seleccionarCliente(String numeroServicio) {
        return modelo.seleccionarCliente(numeroServicio);
    }

    // Consultamos sus datos de consumo.
    public Consumo consultarDatosConsumo() {
        return modelo.consultarDatosConsumo();
    }

    // Validamos que la tarjeta tenga 16 digitos.
    public boolean validarNumeroTarjeta(String numeroTarjeta) {

        return numeroTarjeta != null
                && numeroTarjeta.replaceAll("[ -]", "")
                        .matches("\\d{16}");
    }

    // Validamos la tarjeta y registramos el pago.
    public Pago registrarPago(String numeroTarjeta) {

        if (!validarNumeroTarjeta(numeroTarjeta)) {
            throw new IllegalArgumentException(
                    "La tarjeta debe tener 16 digitos.");
        }

        // Solo enviamos los ultimos cuatro digitos al modelo.
        String tarjeta = numeroTarjeta.replaceAll("[ -]", "");

        return modelo.registrarPago(tarjeta.substring(12));
    }

    // Obtenemos el pago para generar el recibo.
    public Pago generarRecibo() {
        return modelo.obtenerUltimoPago();
    }
}

