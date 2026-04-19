package com.mycompany.restauranteelbuensabor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Factura {
    private final int numeroFactura;
    private final int numeroMesa;
    private final List<ItemPedido> items;
    private final double subtotalBruto;
    private final double descuento;
    private final double subtotalConDescuento;
    private final double iva;
    private final double propina;
    private final double total;

    public Factura(
            int numeroFactura,
            int numeroMesa,
            List<ItemPedido> items,
            double subtotalBruto,
            double descuento,
            double subtotalConDescuento,
            double iva,
            double propina,
            double total) {
        this.numeroFactura = numeroFactura;
        this.numeroMesa = numeroMesa;
        this.items = new ArrayList<>(items);
        this.subtotalBruto = subtotalBruto;
        this.descuento = descuento;
        this.subtotalConDescuento = subtotalConDescuento;
        this.iva = iva;
        this.propina = propina;
        this.total = total;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public List<ItemPedido> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double getSubtotalBruto() {
        return subtotalBruto;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getSubtotalConDescuento() {
        return subtotalConDescuento;
    }

    public double getIva() {
        return iva;
    }

    public double getPropina() {
        return propina;
    }

    public double getTotal() {
        return total;
    }
}
