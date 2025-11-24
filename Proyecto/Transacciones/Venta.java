package udla.ddurand.Proyecto.Transacciones;

public class Venta extends Transaccion {
    /** Creacion de atributos de la subclase */
    private String cliente;
    private double descuento;

    /** Constructores */
    public Venta() {
    }

    public Venta(String fecha, double monto, String numeroTransaccion, String cliente) {
        super(fecha, monto, numeroTransaccion);
        this.cliente = cliente;
        this.descuento = 0.0;
    }

    /** Metodos JAVA*/
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    /** Metodos programador*/
    public double calcularDescuento() {
        double montoTotal = this.getMonto();
        if (montoTotal > 100) {
            this.descuento = montoTotal * 0.10;
        } else if (montoTotal > 50) {
            this.descuento = montoTotal * 0.05;
        } else {
            this.descuento = 0;
        }
        return this.descuento;
    }
}

