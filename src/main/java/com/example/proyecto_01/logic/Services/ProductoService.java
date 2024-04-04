package com.example.proyecto_01.logic.Services;


import com.example.proyecto_01.logic.Productos;
import com.example.proyecto_01.data.ProductoRepository;
import com.example.proyecto_01.logic.Proveedores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final ProveedorService proveedorService; // Inyectar el ProveedorService

    @Autowired
    public ProductoService(ProductoRepository productoRepository, ProveedorService proveedorService) {
        this.productoRepository = productoRepository;
        this.proveedorService = proveedorService; // Asignar el ProveedorService inyectado
    }

    public Productos saveProducto(Productos producto) {
        return productoRepository.save(producto);
    }

    public List<Productos> findAllProductos() {
        return productoRepository.findAll();
    }
    public List<Productos> findProductosByProveedor(Proveedores proveedor) {
        return productoRepository.findByProveedoresByIdProveedor(proveedor);
    }

    public List<Productos> findProductosByProveedorActual(String usuario) {
        Proveedores proveedor = proveedorService.encontrarPorUsuario(usuario); //proveedor actual

        if (proveedor != null) {
            return productoRepository.findByProveedoresByIdProveedor(proveedor); //productos del proveedor actual solo del proveedor actual
        } else {
            //si no encuentra proveedor actual
            return Collections.emptyList();
        }
    }
}

