# Refactorizacion a codigo limpio

Este proyecto toma la base original de `RestauranteElBuenSabor` y la reorganiza para que siga cumpliendo el flujo del taller, pero con una estructura mucho mas mantenible.

## Problemas encontrados en el codigo original

- `Datos.java` concentraba todo el estado del sistema en variables `public static`.
- El calculo de la factura estaba duplicado y repartido entre logica de negocio e impresion.
- El programa usaba arrays paralelos para representar productos y cantidades.
- `RestauranteElBuenSabor.java` mezclaba menu, validaciones, cambios de estado y reglas del negocio en un solo metodo.
- Habia nombres cripticos, numeros magicos, codigo muerto y comentarios que repetian el codigo.
- Se usaban dos `Scanner` sobre `System.in`, algo innecesario y fragil.

## Mejoras aplicadas

- Se introdujeron clases de dominio: `Producto`, `ItemPedido`, `Pedido` y `Factura`.
- La carta del restaurante ahora vive en `Carta`, lo que elimina arrays paralelos y hace mas claro el dominio.
- El calculo de la factura se centralizo en `CalculadorFactura`, con constantes para IVA, propina, descuento y umbrales.
- La emision de facturas se separo en `ServicioFacturacion`, dejando el contador de facturas fuera de la interfaz.
- La impresion de consola se movio a `ImpresorFactura`, evitando mezclar calculo con salida.
- El `main` se redujo a coordinacion del flujo, lectura de opciones y delegacion a clases con responsabilidad unica.
- Se elimino codigo comentado, nombres ambiguos y duplicacion de encabezados y calculos.

## Ajustes logicos importantes

- La propina ahora se calcula sobre el subtotal despues del descuento y antes del IVA, como lo pide la guia del taller.
- La validacion del pedido ya no tiene efectos secundarios ocultos.
- Se usa un unico `Scanner` para toda la aplicacion.
- La lectura de enteros ahora tolera entradas invalidas sin romper la ejecucion.

## Validacion realizada

Se compilo el proyecto con `javac`:

```powershell
$javaFiles = Get-ChildItem -Recurse -Filter '*.java' -Path '.\src\main\java' | ForEach-Object { $_.FullName }
New-Item -ItemType Directory -Path '.\out' -Force | Out-Null
& 'C:\Program Files\Common Files\Oracle\Java\javapath\javac.exe' -d '.\out' $javaFiles
```

La compilacion termino correctamente.

## Estructura resultante

- `Carta`: catalogo de productos.
- `Producto`: nombre y precio de un producto.
- `ItemPedido`: relacion entre producto y cantidad.
- `Pedido`: estado del pedido actual y de la mesa.
- `CalculadorFactura`: reglas de negocio de facturacion.
- `Factura`: resultado del calculo con sus valores desglosados.
- `ServicioFacturacion`: generacion y numeracion de facturas.
- `ImpresorFactura`: salida en consola.
- `RestauranteElBuenSabor`: flujo principal del programa.
