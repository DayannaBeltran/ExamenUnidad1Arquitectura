/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pagoserviciodeluz;

import javax.swing.SwingUtilities;

/**
 *
 * @author daya
 */
public class PagoServicioDeLuz {

    public static void main(String[] args) {
        // Creamos la vista, el modelo y el controlador.
        SwingUtilities.invokeLater(() -> {

            PantallaPagoLuz vista = new PantallaPagoLuz();

            ModeloNegocio modelo = new ModeloNegocio();

            ControlPagoLuz controlador =
                    new ControlPagoLuz(vista, modelo);

            // Conectamos los eventos y mostramos la ventana.
            vista.registrarEventos(controlador);

            vista.setVisible(true);
        });
    }
}
