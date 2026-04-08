package com.omartobar.backendTienda.Entity;

import jakarta.persistence.*;
import lombok.Cleanup;

@Entity
@Table (name = "Clientes")

public class Clientes {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "dpi_cliente")
    private Integer dpiCliente;

    @Column (name = "nombre_cliente")
    private String nombreCliente;

    @Column (name = "apellido_cliente")
    private String apellidoCliente;

    @Column (name = "direccion")
    private String direccion;

    @Column (name = "estado")
    private Integer estado;

    //Getters y Setters//

    public Integer getDpiCliente() {return dpiCliente;}

    public void setDpiCliente(Integer dpiCliente) {this.dpiCliente = dpiCliente;}

    public String getNombreCliente() {return nombreCliente;}

    public void setNombreCliente(String nombreCliente) {this.nombreCliente = nombreCliente;}

    public String getApellidoCliente() {return apellidoCliente;}

    public void setApellidoCliente(String apellidoCliente) {this.apellidoCliente = apellidoCliente;}

    public String getDireccion() {return direccion;}

    public void setDireccion(String direccion) {this.direccion = direccion;}

    public Integer getEstado() {return estado;}

    public void setEstado(Integer estado) {this.estado = estado;}
}
