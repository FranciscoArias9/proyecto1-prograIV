package com.example.proyecto_01.logic;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Productos {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_producto")
    private int idProducto;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "valor")
    private Object valor;
    @Basic
    @Column(name = "id_proveedor")
    private int idProveedor;
    @Basic
    @Column(name = "id_cliente")
    private int idCliente;
    @Basic
    @Column(name = "id_factura")
    private int idFactura;
    @ManyToOne
    @JoinColumn(name = "id_proveedor2", referencedColumnName = "id_proveedor", nullable = false)
    private Proveedores proveedoresByIdProveedor;
    @ManyToOne
    @JoinColumn(name = "id_cliente2", referencedColumnName = "id_cliente", nullable = false)
    private Clientes clientesByIdCliente;
    @ManyToOne
    @JoinColumn(name = "id_factura2", referencedColumnName = "id_factura", nullable = false)
    private Facturas facturasByIdFactura;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Productos productos = (Productos) o;
        return idProducto == productos.idProducto && idProveedor == productos.idProveedor && idCliente == productos.idCliente && idFactura == productos.idFactura && Objects.equals(nombre, productos.nombre) && Objects.equals(valor, productos.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, nombre, valor, idProveedor, idCliente, idFactura);
    }

    public Proveedores getProveedoresByIdProveedor() {
        return proveedoresByIdProveedor;
    }

    public void setProveedoresByIdProveedor(Proveedores proveedoresByIdProveedor) {
        this.proveedoresByIdProveedor = proveedoresByIdProveedor;
    }

    public Clientes getClientesByIdCliente() {
        return clientesByIdCliente;
    }

    public void setClientesByIdCliente(Clientes clientesByIdCliente) {
        this.clientesByIdCliente = clientesByIdCliente;
    }

    public Facturas getFacturasByIdFactura() {
        return facturasByIdFactura;
    }

    public void setFacturasByIdFactura(Facturas facturasByIdFactura) {
        this.facturasByIdFactura = facturasByIdFactura;
    }
}
