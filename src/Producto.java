import java.util.Date;

public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private float precioCompra;
    private float precioVenta;
    private Date fechaIngreso;
    private Categoria categoria;
    private Proveedor proveedor;

    public Producto(int idProducto, String nombre, String descripcion, int cantidad, float precioCompra, float precioVenta, Date fechaIngreso, Categoria categoria, Proveedor proveedor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.fechaIngreso = fechaIngreso;
        this.categoria = categoria;
        this.proveedor = proveedor;
    }

    public Producto(int idProducto, String nombre, String descripcion, int cantidad, float precioCompra, float precioVenta, Date fechaIngreso) {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", cantidad=" + cantidad +
                ", precioCompra=" + precioCompra +
                ", precioVenta=" + precioVenta +
                ", fechaIngreso=" + fechaIngreso +
                '}';
    }
}
