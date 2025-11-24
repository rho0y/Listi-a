package udla.ddurand.Proyecto.Transacciones;

public class Compra extends Transaccion {
    /** Creacion de atributos de la subclase */
    private String proveedor;
    private String fechaEntrega;

    /** Constructores */
    public Compra() {
    }

    public Compra(String fecha, double monto, String numeroTransaccion, String proveedor, String fechaEntrega) {
        super(fecha, monto, numeroTransaccion);
        this.proveedor = proveedor;
        this.fechaEntrega = fechaEntrega;
    }

    /** Metodos JAVA*/
    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    /** Metodos programador*/
}

