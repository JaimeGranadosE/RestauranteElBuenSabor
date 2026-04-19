package com.mycompany.restauranteelbuensabor;

public class CalculadorFactura {
    private static final double TASA_IVA = 0.19;
    private static final double TASA_PROPINA = 0.10;
    private static final double TASA_DESCUENTO = 0.05;
    private static final double UMBRAL_PROPINA = 50000;
    private static final int PRODUCTOS_PARA_DESCUENTO = 3;

    public Factura calcular(int numeroFactura, Pedido pedido) {
        double subtotalBruto = pedido.calcularSubtotalBruto();
        double descuento = calcularDescuento(pedido, subtotalBruto);
        double subtotalConDescuento = subtotalBruto - descuento;
        double iva = subtotalConDescuento * TASA_IVA;
        double propina = subtotalConDescuento > UMBRAL_PROPINA ? subtotalConDescuento * TASA_PROPINA : 0;
        double total = subtotalConDescuento + iva + propina;

        return new Factura(
                numeroFactura,
                pedido.getNumeroMesa(),
                pedido.getItems(),
                subtotalBruto,
                descuento,
                subtotalConDescuento,
                iva,
                propina,
                total);
    }

    private double calcularDescuento(Pedido pedido, double subtotalBruto) {
        if (pedido.contarProductosDiferentes() > PRODUCTOS_PARA_DESCUENTO) {
            return subtotalBruto * TASA_DESCUENTO;
        }
        return 0;
    }
}
