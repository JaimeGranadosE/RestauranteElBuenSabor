package com.mycompany.restauranteelbuensabor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
    private final List<ItemPedido> items;
    private int numeroMesa;
    private boolean mesaActiva;

    public Pedido() {
        items = new ArrayList<>();
        numeroMesa = 0;
        mesaActiva = false;
    }

    public void asignarMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa > 0 ? numeroMesa : 1;
        mesaActiva = true;
    }

    public boolean tieneMesaActiva() {
        return mesaActiva;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void cerrarMesaActual() {
        mesaActiva = false;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        ItemPedido itemExistente = buscarItem(producto);
        if (itemExistente == null) {
            items.add(new ItemPedido(producto, cantidad));
            return;
        }
        itemExistente.agregarCantidad(cantidad);
    }

    public boolean tieneProductos() {
        return !items.isEmpty();
    }

    public List<ItemPedido> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double calcularSubtotalBruto() {
        double subtotal = 0;
        for (ItemPedido item : items) {
            subtotal += item.calcularSubtotal();
        }
        return subtotal;
    }

    public int contarProductosDiferentes() {
        return items.size();
    }

    public void reiniciar() {
        items.clear();
        numeroMesa = 0;
        mesaActiva = false;
    }

    private ItemPedido buscarItem(Producto producto) {
        for (ItemPedido item : items) {
            if (item.getProducto().getNombre().equals(producto.getNombre())) {
                return item;
            }
        }
        return null;
    }
}
