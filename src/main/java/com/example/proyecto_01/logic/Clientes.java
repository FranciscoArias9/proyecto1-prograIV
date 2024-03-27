package com.example.proyecto_01.logic;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Clientes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cliente")
    private int idCliente;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "usuario")
    private String usuario;
    @Basic
    @Column(name = "clave")
    private String clave;
    @Basic
    @Column(name = "id_proveedor")
    private Integer idProveedor;
    @ManyToOne
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    private Proveedores proveedoresByIdProveedor;
    @OneToMany(mappedBy = "clientesByIdCliente")
    private Collection<Facturas> facturasByIdCliente;
    @OneToMany(mappedBy = "clientesByIdCliente")
    private Collection<Productos> productosByIdCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clientes clientes = (Clientes) o;
        return idCliente == clientes.idCliente && Objects.equals(nombre, clientes.nombre) && Objects.equals(usuario, clientes.usuario) && Objects.equals(clave, clientes.clave) && Objects.equals(idProveedor, clientes.idProveedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, nombre, usuario, clave, idProveedor);
    }

    public Proveedores getProveedoresByIdProveedor() {
        return proveedoresByIdProveedor;
    }

    public void setProveedoresByIdProveedor(Proveedores proveedoresByIdProveedor) {
        this.proveedoresByIdProveedor = proveedoresByIdProveedor;
    }

    public Collection<Facturas> getFacturasByIdCliente() {
        return facturasByIdCliente;
    }

    public void setFacturasByIdCliente(Collection<Facturas> facturasByIdCliente) {
        this.facturasByIdCliente = facturasByIdCliente;
    }

    public Collection<Productos> getProductosByIdCliente() {
        return productosByIdCliente;
    }

    public void setProductosByIdCliente(Collection<Productos> productosByIdCliente) {
        this.productosByIdCliente = productosByIdCliente;
    }
}
