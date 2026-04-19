package com.mycompany.restauranteelbuensabor;

public class ItemPedido {
    private final Producto producto;
    private int cantidad;

    public ItemPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void agregarCantidad(int cantidadAdicional) {
        cantidad += cantidadAdicional;
    }

    public double calcularSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}
