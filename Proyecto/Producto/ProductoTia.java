package udla.ddurand.Proyecto.Producto;

public class ProductoTia {
    /** Creacion de atributos */
    protected int codigo;
    protected String nombre;
    protected double precio;
    protected String tipo;
    protected String fechaElaboracion;
    protected String fechaVencimiento;
    private static int contadorId = 1; // Contador estático para asignar IDs únicos
    private int garantiaMeses;
    private String categoria;
    private String disponibilidad;
    private int cantidadP;
    private int demandaAlta;
    private int demandaMedia;
    private int demandaBaja;


    /** Creacion de constructores */

    public ProductoTia() {
    }
    public ProductoTia(String nombre, double precio, String tipo, String fechaElaboracion,
                       String fechaVencimiento, int garantiaMeses, String categoria,
                       String disponibilidad, int cantidadP, int demandaAlta,
                       int demandaMedia, int demandaBaja) {
        this.codigo = contadorId++; // Asigna un ID único y luego incrementa el contador
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.fechaElaboracion = fechaElaboracion;
        this.fechaVencimiento = fechaVencimiento;
        this.garantiaMeses = garantiaMeses;
        this.categoria = categoria;
        this.disponibilidad = disponibilidad;
        this.cantidadP = cantidadP;
        this.demandaAlta = demandaAlta;
        this.demandaMedia = demandaMedia;
        this.demandaBaja = demandaBaja;
    }


    /** Metodos JAVA */
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(String fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        ProductoTia.contadorId = contadorId;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getCantidadP() {
        return cantidadP;
    }

    public void setCantidadP(int cantidadP) {
        this.cantidadP = cantidadP;
    }

    public int getDemandaAlta() {
        return demandaAlta;
    }

    public void setDemandaAlta(int demandaAlta) {
        this.demandaAlta = demandaAlta;
    }

    public int getDemandaMedia() {
        return demandaMedia;
    }

    public void setDemandaMedia(int demandaMedia) {
        this.demandaMedia = demandaMedia;
    }

    public int getDemandaBaja() {
        return demandaBaja;
    }

    public void setDemandaBaja(int demandaBaja) {
        this.demandaBaja = demandaBaja;
    }
/** METODOS PROGRAMADOR */




}
