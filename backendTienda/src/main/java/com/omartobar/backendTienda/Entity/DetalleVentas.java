package com.omartobar.backendTienda.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DetalleVentas")

public class DetalleVentas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_detalle_venta")
    private Integer codigoDetalleVenta;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "codigo_producto")
    private Integer codigoProducto;

    @Column(name = "codigo_venta")
    private Integer codigoVenta;

    // Getters y Setters //

    public Integer getCodigoDetalleVenta() { return codigoDetalleVenta; }

    public void setCodigoDetalleVenta(Integer codigoDetalleVenta) { this.codigoDetalleVenta = codigoDetalleVenta; }

    public Integer getCantidad() { return cantidad; }

    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getPrecioUnitario() { return precioUnitario; }

    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }

    public Double getSubtotal() { return subtotal; }

    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }

    public Integer getCodigoProducto() { return codigoProducto; }

    public void setCodigoProducto(Integer codigoProducto) { this.codigoProducto = codigoProducto; }

    public Integer getCodigoVenta() { return codigoVenta; }

    public void setCodigoVenta(Integer codigoVenta) { this.codigoVenta = codigoVenta; }
}
