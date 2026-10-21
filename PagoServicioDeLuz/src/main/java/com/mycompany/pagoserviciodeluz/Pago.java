/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagoserviciodeluz;

/**
 *
 * @author daya
 */
public class Pago {
     private String folio;
    private String fecha;
    private double importe;
    private String ultimosCuatro;
    private Cliente cliente;
    private boolean registrado;

    public Pago(Cliente cliente, String folio, String fecha,
                double importe, String ultimosCuatro) {

        this.cliente = cliente;
        this.folio = folio;
        this.fecha = fecha;
        this.importe = importe;
        this.ultimosCuatro = ultimosCuatro;
        this.registrado = false;
    }

    // Marcamos el pago como registrado.
    public void guardarDatosPago() {
        registrado = true;
    }

    // Obtenemos la informacion para mostrar el recibo.
    public String obtenerDatosPago() {
        return "RECIBO DE PAGO\n\nPago realizado exitosamente"
                + "\nFolio: " + folio
                + "\nFecha: " + fecha
                + "\nCliente: " + cliente.getNombre()
                + "\nNumero de servicio: " + cliente.getNumeroServicio()
                + "\n" + cliente.getConsumo().obtenerDatosConsumo()
                + "\nTotal pagado: $"
                + String.format(java.util.Locale.US, "%.2f", importe)
                + "\nTarjeta terminacion: " + ultimosCuatro;
    }

    public boolean estaRegistrado() {
        return registrado;
    }
}
