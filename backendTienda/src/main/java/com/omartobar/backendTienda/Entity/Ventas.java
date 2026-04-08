package com.omartobar.backendTienda.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "ventas")

public class Ventas {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_venta")
    private Integer codigoVenta;

    @Column(name = "fecha_venta")
    private java.sql.Date fechaVenta;

    @Column(name = "total")
    private Double total;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "dpi_cliente")
    private Integer dpiCliente;

    @Column(name = "codigo_usuario")
    private Integer codigoUsuario;

    //Getters y Setters//

    public Integer getCodigoVenta() {return codigoVenta;}

    public void setCodigoVenta(Integer codigoVenta) {this.codigoVenta = codigoVenta;}

    public java.sql.Date getFechaVenta() {return fechaVenta;}

    public void setFechaVenta(java.sql.Date fechaVenta) {this.fechaVenta = fechaVenta;}

    public Double getTotal() {return total;}

    public void setTotal(Double total) {this.total = total;}

    public Integer getEstado() {return estado;}

    public void setEstado(Integer estado) {this.estado = estado;}

    public Integer getDpiCliente() {return dpiCliente;}

    public void setDpiCliente(Integer dpiCliente) {this.dpiCliente = dpiCliente;}

    public Integer getCodigoUsuario() {return codigoUsuario;}

    public void setCodigoUsuario(Integer codigoUsuario) {this.codigoUsuario = codigoUsuario;}
}
