package com.mycompany.restauranteelbuensabor;

public class ServicioFacturacion {
    private final CalculadorFactura calculadorFactura;
    private int siguienteNumeroFactura;

    public ServicioFacturacion() {
        calculadorFactura = new CalculadorFactura();
        siguienteNumeroFactura = 1;
    }

    public Factura generarFactura(Pedido pedido) {
        Factura factura = calculadorFactura.calcular(siguienteNumeroFactura, pedido);
        siguienteNumeroFactura++;
        return factura;
    }
}
