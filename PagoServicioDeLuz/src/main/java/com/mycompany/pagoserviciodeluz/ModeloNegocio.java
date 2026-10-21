/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagoserviciodeluz;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author daya
 * 
 */
public class ModeloNegocio {
    private List<Cliente> clientes;
    private List<Pago> pagos;
    private Cliente clienteSeleccionado;

    public ModeloNegocio() {

        clientes = new ArrayList<>();
        pagos = new ArrayList<>();

        // Agregamos clientes de prueba.
        clientes.add(new Cliente("123456789012", "Ana López",
                new Consumo("Septiembre 2026", 250, 650)));

        clientes.add(new Cliente("123456789033", "Juan Pérez",
                new Consumo("Septiembre 2026", 190, 480)));

        clientes.add(new Cliente("123789456012", "María García",
                new Consumo("Septiembre 2026", 300, 780)));
    }

    // Buscamos los clientes que coinciden con el numero ingresado.
    public List<Cliente> buscarClientesCoincidentes(String criterio) {

        List<Cliente> encontrados = new ArrayList<>();

        if (criterio == null || criterio.trim().isEmpty()) {
            return encontrados;
        }

        for (Cliente cliente : clientes) {
            if (cliente.coincideConNumeroServicio(criterio.trim())) {
                encontrados.add(cliente);
            }
        }

        return encontrados;
    }

    // Guardamos al cliente seleccionado.
    public Cliente seleccionarCliente(String numeroServicio) {

        clienteSeleccionado = null;

        for (Cliente cliente : clientes) {
            if (cliente.getNumeroServicio().equals(numeroServicio)) {
                clienteSeleccionado = cliente;
                break;
            }
        }

        return clienteSeleccionado;
    }

    // Consultamos el consumo del cliente.
    public Consumo consultarDatosConsumo() {

        if (clienteSeleccionado == null) {
            return null;
        }

        return clienteSeleccionado.getConsumo();
    }

    // Registramos el pago simulado del servicio.
    public Pago registrarPago(String ultimosCuatro) {

        Consumo consumo = consultarDatosConsumo();

        if (consumo == null) {
            throw new IllegalStateException("Selecciona un cliente.");
        }

        if (consumo.estaPagado()) {
            throw new IllegalStateException("Este servicio ya fue pagado.");
        }

        if (ultimosCuatro == null ||
                !ultimosCuatro.matches("\\d{4}")) {
            throw new IllegalArgumentException(
                    "Los ultimos cuatro digitos son invalidos.");
        }

        // Generamos un folio y la fecha del pago.
        String folio = String.format("PAG-%04d", pagos.size() + 1);

        String fecha = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        // Creamos el pago con los datos del cliente.
        Pago pago = new Pago(clienteSeleccionado, folio, fecha,
                consumo.obtenerImportePendiente(), ultimosCuatro);

        pago.guardarDatosPago();
        pagos.add(pago);

        // Actualizamos el consumo como pagado.
        consumo.marcarComoPagado();

        return pago;
    }

    // Obtenemos el ultimo pago realizado.
    public Pago obtenerUltimoPago() {

        if (pagos.isEmpty()) {
            return null;
        }

        return pagos.get(pagos.size() - 1);
    }
}

