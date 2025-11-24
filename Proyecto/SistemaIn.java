package udla.ddurand.Proyecto;

import udla.ddurand.Proyecto.Bodega.ReporteInventario;
import udla.ddurand.Proyecto.Producto.ProductoTia;
import udla.ddurand.Proyecto.Bodega.Almacen;
import udla.ddurand.Proyecto.Transacciones.Compra;
import udla.ddurand.Proyecto.Transacciones.Transaccion;
import udla.ddurand.Proyecto.Transacciones.Venta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaIn {
    private static Scanner scanner = new Scanner(System.in);
    //Listas para almacenar los productos

    private static List<Almacen> listaAlmacenes = new ArrayList<>();

    private static List<Transaccion> listaTransacciones = new ArrayList<>();

    private static List<Compra> listaCompras = new ArrayList<>();

    private static List<Venta> listaVentas = new ArrayList<>();

    private static List<ReporteInventario> listaReportes = new ArrayList<>();

    private static List<ProductoTia> listaproductos = new ArrayList<>();

    private static boolean bodegaGestionada = false;

    private static long capacidadMaximaBodega = 0;

    private static void mostrarMenu() {
        System.out.println("\n===========================================");
        System.out.println("          SISTEMA DE GESTIÓN INVENTARIO          ");
        System.out.println("===========================================");
        System.out.println("1.  Control de bodega");
        System.out.println("2.  Ingresar producto");
        System.out.println("3.  Realizar venta");
        System.out.println("4.  Reabastecer producto");
        System.out.println("5.  Editar Producto");
        System.out.println("6.  Eliminar Producto");
        System.out.println("7.  Mostrar reporte");
        System.out.println("8. Salir");
        System.out.print(">>> Ingrese su opcion: ");
    }

    public static void main(String[] args) {
        int opc;
        do {
            mostrarMenu();
            if (scanner.hasNextInt()) {
                opc = scanner.nextInt();
                scanner.nextLine(); // Consumir la línea pendiente

                switch (opc) {
                    case 1:
                        gestionBodega();
                        break;
                    case 2:
                        ingresarProducto();
                        break;
                    case 3:
                        realizarVenta();
                        break;
                    case 4:
                        reabastecerProducto();
                        break;
                    case 5:
                        editarProducto();
                        break;
                    case 6:
                        eliminarProducto();
                        break;
                    case 7:
                        mostrarReporte();
                        break;
                    case 8:
                        System.out.println("\n Saliendo de la aplicación. ¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("\n Opción no válida. Inténtelo de nuevo.");
                }
            } else {
                System.out.println("\n Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Consumir la entrada no válida
                opc = 0; // Para que el bucle continúe
            }

            if (opc != 8) {
                System.out.println("\n-------------------------------------------");
            }
        } while (opc != 8);
        scanner.close();


    }
/** METODO PARA INGRESAR PRODUCTO */
private static void ingresarProducto() {
    if (!bodegaGestionada) {
        System.out.println("\n Debe gestionar la bodega antes de ingresar productos.");
        bodegaGestionada = false;
        return;
    } else{
        System.out.println("\n--- Ingreso de Nuevo Producto ---");
        System.out.print("Ingrese nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese precio: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("\n Debe ingresar un número válido.");
            scanner.nextLine();
            System.out.print("Ingrese precio: ");
        }
        double precio = scanner.nextDouble();
        scanner.nextLine();
        if (precio < 0) {
            System.out.println("\n El precio no puede ser negativo.");
            return;
        }

        System.out.print("Ingrese tipo: ");
        String tipo = scanner.nextLine();

        System.out.println("\nIngrese si el producto es perecible o no: ");
        System.out.println("1. Es perecible");
        System.out.println("2. No es perecible");
        System.out.print(">>> Seleccione: ");
        int opcionPerecible = scanner.nextInt();
        scanner.nextLine();

        String fechaElaboracion = "";
        String fechaVencimiento = "";

        System.out.print("Ingrese fecha de elaboración (dd/mm/aaaa): ");
        fechaElaboracion = scanner.nextLine();

        if (opcionPerecible == 1) {
            System.out.print("Ingrese fecha de vencimiento (dd/mm/aaaa): ");
            fechaVencimiento = scanner.nextLine();
        } else if (opcionPerecible == 2) {
            fechaVencimiento = "N/A";
        } else {
            System.out.println("\n Opción no válida.");
            return;
        }

        System.out.print("Ingrese garantía en meses: ");
        while (!scanner.hasNextInt()) {
            System.out.println("\n Debe ingresar un número válido.");
            scanner.nextLine();
            System.out.print("Ingrese garantía en meses: ");
        }
        int garantiaMeses = scanner.nextInt();
        scanner.nextLine();
        if (garantiaMeses < 0) {
            System.out.println("\n La garantía no puede ser negativa.");
            return;
        }


        System.out.print("Ingrese cantidad: ");
        while (!scanner.hasNextInt()) {
            System.out.println("\n Debe ingresar un número válido.");
            scanner.nextLine();
            System.out.print("Ingrese cantidad: ");
        }
        int cantidad = scanner.nextInt();
        scanner.nextLine();
        if (cantidad < 0) {
            System.out.println("\n La cantidad no puede ser negativa.");
            return;
        }

        long cantidadActual = obtenerCantidadTotal();
        if (cantidadActual + cantidad > capacidadMaximaBodega) {
            System.out.println("\n No se puede ingresar. Excede la capacidad máxima de la bodega.");
            System.out.println("Capacidad actual: " + cantidadActual);
            System.out.println("Capacidad máxima: " + capacidadMaximaBodega);
            System.out.println("Cantidad que se puede agregar: " + (capacidadMaximaBodega - cantidadActual));
            return;
        }

        System.out.println("\nDisponibilidad:");
        System.out.println("1. Disponible");
        System.out.println("2. No disponible");
        System.out.print(">>> Seleccione disponibilidad: ");
        int opci = scanner.nextInt();
        scanner.nextLine();

        String disponibilidad = (opci == 1) ? "Disponible" : "No disponible";

        System.out.println("\nNivel de demanda:");
        System.out.println("1. Alta");
        System.out.println("2. Media");
        System.out.println("3. Baja");
        System.out.print(">>> Seleccione nivel de demanda: ");
        int opcionDemanda = scanner.nextInt();
        scanner.nextLine();

        int demandaAlta = 0, demandaMedia = 0, demandaBaja = 0;

        switch (opcionDemanda) {
            case 1:
                demandaAlta = 1;
                break;
            case 2:
                demandaMedia = 1;
                break;
            case 3:
                demandaBaja = 1;
                break;
            default:
                demandaBaja = 1;
                break;
        }

        ProductoTia nuevoProducto = new ProductoTia(
                nombre,
                precio,
                tipo,
                fechaElaboracion,
                fechaVencimiento,
                garantiaMeses,
                tipo,
                disponibilidad,
                cantidad,
                demandaAlta,
                demandaMedia,
                demandaBaja
        );

        listaproductos.add(nuevoProducto);
        System.out.println("\nProducto ingresado con éxito. Código asignado: " + nuevoProducto.getCodigo());
    }
}

/** METODO PARA GESTIONAR BODEGA */
    private static void gestionBodega() {
        System.out.println("\n--- Gestión de Bodega ---");
        System.out.println("Ingrese la capacidad máxima de la bodega");
        capacidadMaximaBodega = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Ingrese la cantidad máxima de productos");
        int cantidadMaxima = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese la ubicación de la bodega");
        String ubicacion = scanner.nextLine();

        bodegaGestionada = true;
        System.out.println("\n Bodega gestionada con exito.");
    }
/** METODO PARA REALIZAR VENTA */
    private static void realizarVenta() {
        if (listaproductos.isEmpty()) {
            System.out.println("\n No hay productos para vender.");
            return;
        }

        System.out.println("\n--- Realizar Venta ---");
        System.out.print("Ingrese el código del producto: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        boolean encontrado = false;
        for (int i = 0; i < listaproductos.size(); i++) {
            if (listaproductos.get(i).getCodigo() == codigo) {
                ProductoTia producto = listaproductos.get(i);

                // VALIDACIÓN: Verificar si el producto está disponible
                if (!producto.getDisponibilidad().equals("Disponible")) {
                    System.out.println("\n Producto no disponible. No se puede realizar la venta.");
                    return;
                }

                // VALIDACIÓN: Verificar si el producto está vencido
                System.out.print("Ingrese fecha de compra (dd/mm/aaaa): ");
                String fechaCompra = scanner.nextLine();

                if (estaVencido(producto.getFechaVencimiento(), fechaCompra)) {
                    System.out.println("\n Producto vencido. No se puede realizar la venta.");
                    return;
                }

                System.out.print("Cantidad a vender: ");
                int cantidad = scanner.nextInt();
                scanner.nextLine();

                if (cantidad <= 0) {
                    System.out.println("\n La cantidad debe ser mayor que cero.");
                    return;
                }

                if (cantidad > producto.getCantidadP()) {
                    System.out.println("\n Stock insuficiente. Cantidad disponible: " + producto.getCantidadP());
                    return;
                }

                double monto = producto.getPrecio() * cantidad;

                // DESCUENTO: Si el monto supera $5, aplica 5% de descuento
                double descuento = 0;
                if (monto > 5) {
                    descuento = monto * 0.05;
                    monto = monto - descuento;
                    System.out.println("\n¡Descuento aplicado! 5% por compra mayor a $5");
                    System.out.printf("Monto original: $%.2f%n", producto.getPrecio() * cantidad);
                    System.out.printf("Descuento: $%.2f%n", descuento);
                    System.out.printf("Monto final: $%.2f%n", monto);
                }

                System.out.print("Ingrese nombre del cliente: ");
                String cliente = scanner.nextLine();

                String numeroTransaccion = "V-" + (listaVentas.size() + 1);

                Venta venta = new Venta(fechaCompra, monto, numeroTransaccion, cliente);
                listaVentas.add(venta);
                listaTransacciones.add(venta);

                producto.setCantidadP(producto.getCantidadP() - cantidad);

                encontrado = true;
                System.out.println("\n Venta realizada con éxito. Stock restante: " + producto.getCantidadP());
                break;
            }
        }

        if (!encontrado) {
            System.out.println("\n No se encontró ningún producto con código " + codigo + ".");
        }
    }
/** METODO PARA REABASTECER PRODUCTO */
    private static void reabastecerProducto() {
        if (listaproductos.isEmpty()) {
            System.out.println("\n No hay productos registrados.");
            return;
        }

        System.out.println("\n--- Reabastecer Producto ---");
        System.out.print("Ingrese el código del producto: ");

        if (!scanner.hasNextInt()) {
            System.out.println("\n Debe ingresar un número válido.");
            scanner.nextLine();
            return;
        }

        int codigo = scanner.nextInt();
        scanner.nextLine();

        boolean encontrado = false;
        for (int i = 0; i < listaproductos.size(); i++) {
            if (listaproductos.get(i).getCodigo() == codigo) {
                ProductoTia producto = listaproductos.get(i);

                System.out.print("Ingrese la cantidad a reabastecer: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("\n Debe ingresar un número válido.");
                    scanner.nextLine();
                    return;
                }

                int cantidad = scanner.nextInt();
                scanner.nextLine();

                if (cantidad <= 0) {
                    System.out.println("\n La cantidad debe ser mayor que cero.");
                    return;
                }

                long cantidadActual = obtenerCantidadTotal();
                if (cantidadActual + cantidad > capacidadMaximaBodega) {
                    System.out.println("\n No se puede reabastecer. Excede la capacidad máxima de la bodega.");
                    System.out.println("Capacidad actual: " + cantidadActual);
                    System.out.println("Capacidad máxima: " + capacidadMaximaBodega);
                    System.out.println("Cantidad que se puede agregar: " + (capacidadMaximaBodega - cantidadActual));
                    return;
                }

                producto.setCantidadP(producto.getCantidadP() + cantidad);

                System.out.print("Ingrese fecha de reabastecimiento (dd/mm/aaaa): ");
                String fecha = scanner.nextLine();

                encontrado = true;
                System.out.println("\n Producto reabastecido con éxito. Stock actual: " + producto.getCantidadP());
                break;
            }
        }

        if (!encontrado) {
            System.out.println("\n No se encontró ningún producto con código " + codigo + ".");
        }
    }
/** METODO PARA ELIMINAR PRODUCTO */
    private static void eliminarProducto() {
        if (listaproductos.isEmpty()) {
            System.out.println("\n No hay productos registrados. Debe ingresar productos primero.");
            return;
        }

        System.out.println("\n--- Eliminar Producto ---");
        System.out.print("Ingrese el código del producto a eliminar: ");

        if (!scanner.hasNextInt()) {
            System.out.println("\n Debe ingresar un número válido.");
            scanner.nextLine();
            return;
        }

        int codigo = scanner.nextInt();
        scanner.nextLine();

        boolean encontrado = false;
        for (int i = 0; i < listaproductos.size(); i++) {
            if (listaproductos.get(i).getCodigo() == codigo) {
                listaproductos.remove(i);
                encontrado = true;
                System.out.println("\n Producto con código " + codigo + " eliminado con éxito.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("\n No se encontró ningún producto con código " + codigo + ".");
        }
    }
    /** METODO PARA EDITAR PRODUCTO */
    private static void editarProducto() {
        if (listaproductos.isEmpty()) {
            System.out.println("\n No hay productos registrados. Debe ingresar productos primero.");
            return;
        }

        System.out.println("\n--- Editar Producto ---");
        System.out.print("Ingrese el código del producto a editar: ");

        if (!scanner.hasNextInt()) {
            System.out.println("\n Debe ingresar un número válido.");
            scanner.nextLine();
            return;
        }

        int codigo = scanner.nextInt();
        scanner.nextLine();

        boolean encontrado = false;
        for (int i = 0; i < listaproductos.size(); i++) {
            if (listaproductos.get(i).getCodigo() == codigo) {
                ProductoTia producto = listaproductos.get(i);

                System.out.println("\n--- Datos Actuales ---");
                System.out.println("Nombre: " + producto.getNombre());
                System.out.println("Precio: " + producto.getPrecio());
                System.out.println("Tipo: " + producto.getTipo());
                System.out.println("Cantidad: " + producto.getCantidadP());
                System.out.println("Disponibilidad: " + producto.getDisponibilidad());

                System.out.println("\n--- Ingrese los Nuevos Datos ---");

                System.out.print("Nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();

                System.out.print("Nuevo precio: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("\n Debe ingresar un número válido.");
                    scanner.nextLine();
                    return;
                }

                double nuevoPrecio = scanner.nextDouble();
                scanner.nextLine();

                if (nuevoPrecio < 0) {
                    System.out.println("\n El precio no puede ser negativo.");
                    return;
                }

                System.out.print("Nuevo tipo: ");
                String nuevoTipo = scanner.nextLine();

                System.out.print("Nueva fecha de elaboración (dd/mm/aaaa): ");
                String nuevaFechaElaboracion = scanner.nextLine();

                System.out.println("\n¿Es perecible?");
                System.out.println("1. Sí");
                System.out.println("2. No");
                System.out.print(">>> Seleccione: ");
                int opcionPerecible = scanner.nextInt();
                scanner.nextLine();

                String nuevaFechaVencimiento = "";
                if (opcionPerecible == 1) {
                    boolean fechaValida = false;
                    while (!fechaValida) {
                        System.out.print("Nueva fecha de vencimiento (dd/mm/aaaa): ");
                        nuevaFechaVencimiento = scanner.nextLine();

                        int diaE = Integer.parseInt(nuevaFechaElaboracion.charAt(0) + "" + nuevaFechaElaboracion.charAt(1));
                        int mesE = Integer.parseInt(nuevaFechaElaboracion.charAt(3) + "" + nuevaFechaElaboracion.charAt(4));
                        int anoE = Integer.parseInt(nuevaFechaElaboracion.charAt(6) + "" + nuevaFechaElaboracion.charAt(7) + "" + nuevaFechaElaboracion.charAt(8) + "" + nuevaFechaElaboracion.charAt(9));

                        int diaV = Integer.parseInt(nuevaFechaVencimiento.charAt(0) + "" + nuevaFechaVencimiento.charAt(1));
                        int mesV = Integer.parseInt(nuevaFechaVencimiento.charAt(3) + "" + nuevaFechaVencimiento.charAt(4));
                        int anoV = Integer.parseInt(nuevaFechaVencimiento.charAt(6) + "" + nuevaFechaVencimiento.charAt(7) + "" + nuevaFechaVencimiento.charAt(8) + "" + nuevaFechaVencimiento.charAt(9));

                        if (anoV > anoE || (anoV == anoE && mesV > mesE) || (anoV == anoE && mesV == mesE && diaV >= diaE)) {
                            fechaValida = true;
                        } else {
                            System.out.println("\n La fecha de vencimiento no puede ser menor a la fecha de elaboración.");
                        }
                    }
                } else if (opcionPerecible == 2) {
                    nuevaFechaVencimiento = "N/A";
                } else {
                    System.out.println("\n Opción no válida.");
                    return;
                }

                System.out.print("Nueva garantía en meses: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("\n Debe ingresar un número válido.");
                    scanner.nextLine();
                    return;
                }

                int nuevaGarantia = scanner.nextInt();
                scanner.nextLine();

                if (nuevaGarantia < 0) {
                    System.out.println("\n La garantía no puede ser negativa.");
                    return;
                }

                System.out.print("Nueva cantidad: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("\n Debe ingresar un número válido.");
                    scanner.nextLine();
                    return;
                }

                int nuevaCantidad = scanner.nextInt();
                scanner.nextLine();

                if (nuevaCantidad < 0) {
                    System.out.println("\n La cantidad no puede ser negativa.");
                    return;
                }

                long cantidadActual = obtenerCantidadTotal();
                long diferencia = nuevaCantidad - producto.getCantidadP();

                if (cantidadActual + diferencia > capacidadMaximaBodega) {
                    System.out.println("\n No se puede actualizar. Excede la capacidad máxima de la bodega.");
                    System.out.println("Capacidad actual: " + cantidadActual);
                    System.out.println("Capacidad máxima: " + capacidadMaximaBodega);
                    System.out.println("Cantidad máxima permitida: " + (capacidadMaximaBodega - cantidadActual + producto.getCantidadP()));
                    return;
                }

                System.out.println("\nNueva disponibilidad:");
                System.out.println("1. Disponible");
                System.out.println("2. No disponible");
                System.out.print(">>> Seleccione disponibilidad: ");
                int opci = scanner.nextInt();
                scanner.nextLine();

                String nuevaDisponibilidad = (opci == 1) ? "Disponible" : "No disponible";

                producto.setNombre(nuevoNombre);
                producto.setPrecio(nuevoPrecio);
                producto.setTipo(nuevoTipo);
                producto.setFechaElaboracion(nuevaFechaElaboracion);
                producto.setFechaVencimiento(nuevaFechaVencimiento);
                producto.setGarantiaMeses(nuevaGarantia);
                producto.setCantidadP(nuevaCantidad);
                producto.setDisponibilidad(nuevaDisponibilidad);

                encontrado = true;
                System.out.println("\n Producto actualizado con éxito.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("\n No se encontró ningún producto con código " + codigo + ".");
        }
    }
    /** METODO PARA MOSTRAR REPORTE */
    private static void mostrarReporte() {
        if (listaproductos.isEmpty()) {
            System.out.println("\n No hay productos registrados. Debe ingresar productos primero.");
            return;
        }

        System.out.println("\n--- Reporte del Sistema ---");
        System.out.println("1. Ver Registro de Stock");
        System.out.println("2. Ver Registro de Ventas");
        System.out.print(">>> Seleccione opción: ");

        if (!scanner.hasNextInt()) {
            System.out.println("\n Opción no válida.");
            scanner.nextLine();
            return;
        }

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                mostrarRegistroStock();
                break;
            case 2:
                mostrarRegistroVentas();
                break;
            default:
                System.out.println("\n Opción no válida.");
        }
    }
/** METODOS AUXILIARES PARA MOSTRAR REPORTES */
    private static void mostrarRegistroStock() {
        if (listaproductos.isEmpty()) {
            System.out.println("\n No hay productos en el stock.");
            return;
        }

        System.out.println("\n=== REGISTRO DE STOCK ===");
        System.out.printf("%-8s %-20s %-12s %-10s %-15s %-12s %-15s%n",
                "Código", "Nombre", "Precio", "Cantidad", "Tipo", "Disponibilidad", "Demanda");
        System.out.println("------------------------------------------------------------------------------------");

        for (ProductoTia producto : listaproductos) {
            String demanda = producto.getDemandaAlta() == 1 ? "Alta" :
                    producto.getDemandaMedia() == 1 ? "Media" : "Baja";
            System.out.printf("%-8d %-20s $%-11.2f %-10d %-15s %-12s %-15s%n",
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getCantidadP(),
                    producto.getTipo(),
                    producto.getDisponibilidad(),
                    demanda);
        }
    }

    private static void mostrarRegistroVentas() {
        if (listaVentas.isEmpty()) {
            System.out.println("\n No hay ventas registradas.");
            return;
        }

        System.out.println("\n=== REGISTRO DE VENTAS ===");
        System.out.printf("%-15s %-15s %-20s %-15s%n",
                "Transacción", "Monto", "Cliente", "Fecha");
        System.out.println("------------------------------------------------------------------------");

        for (Venta venta : listaVentas) {
            System.out.printf("%-15s $%-14.2f %-20s %-15s%n",
                    venta.getNumeroTransaccion(),
                    venta.getMonto(),
                    venta.getCliente(),
                    venta.getFecha());
        }
    }

    private static boolean estaVencido(String fechaVencimiento, String fechaCompra) {
        // Si no es perecible, no está vencido
        if (fechaVencimiento.equals("N/A")) {
            return false;
        }

        String[] partsVencimiento = fechaVencimiento.split("/");
        String[] partsCompra = fechaCompra.split("/");

        int diaV = Integer.parseInt(partsVencimiento[0]);
        int mesV = Integer.parseInt(partsVencimiento[1]);
        int anoV = Integer.parseInt(partsVencimiento[2]);

        int diaC = Integer.parseInt(partsCompra[0]);
        int mesC = Integer.parseInt(partsCompra[1]);
        int anoC = Integer.parseInt(partsCompra[2]);

        if (anoC > anoV) return true;
        if (anoC < anoV) return false;

        if (mesC > mesV) return true;
        if (mesC < mesV) return false;

        return diaC > diaV;
    }
    private static long obtenerCantidadTotal() {
        long total = 0;
        for (int i = 0; i < listaproductos.size(); i++) {
            total += listaproductos.get(i).getCantidadP();
        }
        return total;
    }



}

