package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

public class RestauranteElBuenSabor {
    private final Carta carta;
    private final Pedido pedidoActual;
    private final ServicioFacturacion servicioFacturacion;
    private final ImpresorFactura impresor;
    private final Scanner scanner;
    private int intentosInvalidos;

    public RestauranteElBuenSabor() {
        carta = Carta.crearCartaBase();
        pedidoActual = new Pedido();
        servicioFacturacion = new ServicioFacturacion();
        impresor = new ImpresorFactura();
        scanner = new Scanner(System.in);
        intentosInvalidos = 0;
    }

    public static void main(String[] args) {
        new RestauranteElBuenSabor().ejecutar();
    }

    public void ejecutar() {
        impresor.imprimirBienvenida();

        boolean aplicacionActiva = true;
        while (aplicacionActiva) {
            mostrarMenuPrincipal();
            int opcionMenu = leerEntero("Seleccione una opcion: ");

            switch (opcionMenu) {
                case 1:
                    mostrarCarta();
                    break;
                case 2:
                    agregarProductoAlPedido();
                    break;
                case 3:
                    mostrarPedidoActual();
                    break;
                case 4:
                    generarFactura();
                    break;
                case 5:
                    prepararNuevaMesa();
                    break;
                case 0:
                    aplicacionActiva = false;
                    System.out.println("Hasta luego!");
                    break;
                default:
                    manejarOpcionInvalida();
                    break;
            }
        }

        scanner.close();
    }

    private void mostrarMenuPrincipal() {
        System.out.println("1. Ver carta");
        System.out.println("2. Agregar producto al pedido");
        System.out.println("3. Ver pedido actual");
        System.out.println("4. Generar factura");
        System.out.println("5. Nueva mesa");
        System.out.println("0. Salir");
        System.out.println("========================================");
    }

    private void mostrarCarta() {
        impresor.imprimirCarta(carta);
        System.out.println();
    }

    private void agregarProductoAlPedido() {
        System.out.println("--- AGREGAR PRODUCTO ---");
        int numeroProducto = leerEntero("Numero de producto (1-" + carta.getCantidadProductos() + "): ");
        int cantidad = leerEntero("Cantidad: ");

        Producto producto = carta.buscarProducto(numeroProducto);
        if (producto == null) {
            mostrarErrorProducto(numeroProducto);
            System.out.println();
            return;
        }

        if (cantidad <= 0) {
            mostrarErrorCantidad(cantidad);
            System.out.println();
            return;
        }

        if (!pedidoActual.tieneMesaActiva()) {
            int numeroMesa = leerEntero("Ingrese numero de mesa: ");
            pedidoActual.asignarMesa(numeroMesa);
        }

        pedidoActual.agregarProducto(producto, cantidad);
        System.out.println("Producto agregado al pedido.");
        System.out.println("  -> " + producto.getNombre() + " x" + cantidad);
        System.out.println();
    }

    private void mostrarPedidoActual() {
        System.out.println();
        if (!pedidoActual.tieneProductos()) {
            System.out.println("No hay productos en el pedido actual.");
            System.out.println("Use la opcion 2 para agregar productos.");
            System.out.println();
            return;
        }

        impresor.imprimirPedido(pedidoActual);
        System.out.println();
    }

    private void generarFactura() {
        System.out.println();
        if (!pedidoActual.tieneProductos()) {
            System.out.println("No se puede generar factura.");
            System.out.println("No hay productos en el pedido.");
            System.out.println("Use la opcion 2 para agregar productos primero.");
            return;
        }

        Factura factura = servicioFacturacion.generarFactura(pedidoActual);
        impresor.imprimirFacturaCompleta(factura);
        pedidoActual.cerrarMesaActual();
        System.out.println();
    }

    private void prepararNuevaMesa() {
        System.out.println();
        pedidoActual.reiniciar();
        System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
        System.out.println();
    }

    private void manejarOpcionInvalida() {
        intentosInvalidos++;
        System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
        if (intentosInvalidos > 3) {
            System.out.println("Demasiados intentos invalidos.");
            intentosInvalidos = 0;
        }
    }

    private void mostrarErrorProducto(int numeroProducto) {
        if (numeroProducto <= 0) {
            System.out.println("El numero debe ser mayor a cero.");
            return;
        }
        System.out.println("Producto no existe. La carta tiene " + carta.getCantidadProductos() + " productos.");
    }

    private void mostrarErrorCantidad(int cantidad) {
        if (cantidad == 0) {
            System.out.println("La cantidad no puede ser cero.");
            return;
        }
        System.out.println("Cantidad invalida. Ingrese un valor positivo.");
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (!scanner.hasNext()) {
                return 0;
            }
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            System.out.println("Ingrese un numero valido.");
            scanner.next();
        }
    }
}
