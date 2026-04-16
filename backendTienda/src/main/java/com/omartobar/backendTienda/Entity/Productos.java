package com.omartobar.backendTienda.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")

public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto")
    private Integer codigoProducto;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "estado")
    private Integer estado;

    // Getters y Setters //

    public Integer getCodigoProducto() { return codigoProducto; }

    public void setCodigoProducto(Integer codigoProducto) { this.codigoProducto = codigoProducto; }

    public String getNombreProducto() { return nombreProducto; }

    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public Double getPrecio() { return precio; }

    public void setPrecio(Double precio) { this.precio = precio; }

    public Integer getStock() { return stock; }

    public void setStock(Integer stock) { this.stock = stock; }

    public Integer getEstado() { return estado; }

    public void setEstado(Integer estado) { this.estado = estado; }
}