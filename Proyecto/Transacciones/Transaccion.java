package udla.ddurand.Proyecto.Transacciones;

public class Transaccion {

    /** CREACION DE ATRIBUTOS */
    private String fecha;
    private double monto;
    private String numeroTransaccion;

    /** CONSTRUCTORES */
    public Transaccion() {
    }

    public Transaccion(String fecha, double monto, String numeroTransaccion) {
        this.fecha = fecha;
        this.monto = monto;
        this.numeroTransaccion = numeroTransaccion;
    }

    /** METODOS JAVA */
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getNumeroTransaccion() {
        return numeroTransaccion;
    }

    public void setNumeroTransaccion(String numeroTransaccion) {
        this.numeroTransaccion = numeroTransaccion;
    }
}
