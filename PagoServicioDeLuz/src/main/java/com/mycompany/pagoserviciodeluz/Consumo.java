/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagoserviciodeluz;

/**
 *
 * @author daya
 */
public class Consumo {
     private String periodo;
    private double consumoKwh;
    private double importePendiente;
    private boolean pagado;

    public Consumo(String periodo, double consumoKwh, double importePendiente) {
        this.periodo = periodo;
        this.consumoKwh = consumoKwh;
        this.importePendiente = importePendiente;
        this.pagado = false;
    }

    // Obtenemos los datos del consumo del cliente.
    public String obtenerDatosConsumo() {
        return "Periodo: " + periodo
                + "\nConsumo: " + consumoKwh + " kWh"
                + "\nTotal pendiente: $"
                + String.format(java.util.Locale.US, "%.2f", importePendiente)
                + "\nEstado: " + (pagado ? "Pagado" : "Pendiente");
    }

    public double obtenerImportePendiente() {
        return importePendiente;
    }

    public boolean estaPagado() {
        return pagado;
    }

    // Actualizamos el estado cuando el cliente paga.
    public void marcarComoPagado() {
        pagado = true;
        importePendiente = 0;
    }
}

