package com.mycompany.restauranteelbuensabor;

public class ImpresorFactura {
    private static final String NOMBRE_RESTAURANTE = "RESTAURANTE EL BUEN SABOR";
    private static final String DIRECCION = "Calle 15 #8-32, Valledupar";
    private static final String NIT = "900.123.456-7";
    private static final String SEPARADOR = "========================================";
    private static final String SEPARADOR_CORTO = "----------------------------------------";

    public void imprimirBienvenida() {
        imprimirEncabezado();
    }

    public void imprimirCarta(Carta carta) {
        System.out.println(SEPARADOR);
        System.out.println("    " + NOMBRE_RESTAURANTE);
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR);

        int indice = 1;
        for (Producto producto : carta.getProductos()) {
            System.out.printf("%d. %-22s $%,.0f%n", indice, producto.getNombre(), producto.getPrecio());
            indice++;
        }

        System.out.println(SEPARADOR);
    }

    public void imprimirPedido(Pedido pedido) {
        System.out.println("--- PEDIDO ACTUAL ---");
        for (ItemPedido item : pedido.getItems()) {
            System.out.printf(
                    "%-20s x%-6d $%,.0f%n",
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.calcularSubtotal());
        }
        System.out.println("--------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", pedido.calcularSubtotalBruto());
    }

    public void imprimirFacturaCompleta(Factura factura) {
        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d%n", factura.getNumeroFactura());
        if (factura.getNumeroMesa() > 0) {
            System.out.printf("Mesa: %d%n", factura.getNumeroMesa());
        }
        System.out.println(SEPARADOR_CORTO);

        for (ItemPedido item : factura.getItems()) {
            System.out.printf(
                    "%-20s x%-6d $%,.0f%n",
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.calcularSubtotal());
        }

        System.out.println(SEPARADOR_CORTO);
        if (factura.getDescuento() > 0) {
            System.out.printf("%-27s $%,.0f%n", "Subtotal:", factura.getSubtotalBruto());
            System.out.printf("%-27s $%,.0f%n", "Descuento (5%):", factura.getDescuento());
            System.out.printf("%-27s $%,.0f%n", "Subtotal con descuento:", factura.getSubtotalConDescuento());
        } else {
            System.out.printf("%-27s $%,.0f%n", "Subtotal:", factura.getSubtotalConDescuento());
        }
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", factura.getIva());
        if (factura.getPropina() > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", factura.getPropina());
        }
        System.out.println(SEPARADOR_CORTO);
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", factura.getTotal());
        System.out.println(SEPARADOR);
        System.out.println("Gracias por su visita!");
        System.out.println("El Buen Sabor - Valledupar");
        System.out.println(SEPARADOR);
    }

    private void imprimirEncabezado() {
        System.out.println(SEPARADOR);
        System.out.println("    " + NOMBRE_RESTAURANTE);
        System.out.println("    " + DIRECCION);
        System.out.println("    NIT: " + NIT);
        System.out.println(SEPARADOR);
    }
}
