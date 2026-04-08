package com.omartobar.backendTienda.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")

public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_usuario")
    private Integer codigoUsuario;

    @Column(name = "username")
    private String username;

    @Column(name = "pasword")
    private String pasword;

    @Column(name = "email")
    private String email;

    @Column(name = "rol")
    private String rol;

    @Column(name = "estado")
    private Integer estado;

    // Getters y Setters //

    public Integer getCodigoUsuario() { return codigoUsuario; }

    public void setCodigoUsuario(Integer codigoUsuario) { this.codigoUsuario = codigoUsuario; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPasword() { return pasword; }

    public void setPasword(String pasword) { this.pasword = pasword; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getRol() { return rol; }

    public void setRol(String rol) { this.rol = rol; }

    public Integer getEstado() { return estado; }

    public void setEstado(Integer estado) { this.estado = estado; }
}