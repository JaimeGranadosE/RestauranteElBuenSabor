package com.mycompany.restauranteelbuensabor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carta {
    private final List<Producto> productos;

    public Carta(List<Producto> productos) {
        this.productos = new ArrayList<>(productos);
    }

    public static Carta crearCartaBase() {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Bandeja Paisa", 32000));
        productos.add(new Producto("Sancocho de Gallina", 28000));
        productos.add(new Producto("Arepa con Huevo", 8000));
        productos.add(new Producto("Jugo Natural", 7000));
        productos.add(new Producto("Gaseosa", 4500));
        productos.add(new Producto("Cerveza Poker", 6000));
        productos.add(new Producto("Agua Panela", 3500));
        productos.add(new Producto("Arroz con Pollo", 25000));
        return new Carta(productos);
    }

    public List<Producto> getProductos() {
        return Collections.unmodifiableList(productos);
    }

    public int getCantidadProductos() {
        return productos.size();
    }

    public Producto buscarProducto(int numeroProducto) {
        if (numeroProducto < 1 || numeroProducto > productos.size()) {
            return null;
        }
        return productos.get(numeroProducto - 1);
    }
}
