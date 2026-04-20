# Refactorizacion de codigo limpio

En este taller se tomo el proyecto `RestauranteElBuenSabor` y se mejoro sin cambiar su funcionamiento principal. La idea fue pasar de un codigo con muchas malas practicas a uno mas ordenado y facil de mantener.

## Problemas que tenia

- Uso de muchas variables `public static`.
- Clases con demasiadas responsabilidades.
- Logica de factura repetida en varias partes.
- Nombres poco claros.
- Numeros magicos en descuentos, IVA y propina.
- Uso de arrays paralelos para manejar productos.

## Cambios principales

- Se crearon clases mas claras como `Producto`, `Pedido`, `Factura` y `Carta`.
- Se separo la logica de calculo en `CalculadorFactura`.
- Se separo la impresion en `ImpresorFactura`.
- El `main` quedo mas limpio y solo controla el flujo del menu.
- Se elimino codigo viejo, comentarios innecesarios y el segundo `Scanner`.

## Nota

Se reviso que el programa compilara bien despues de los cambios.
