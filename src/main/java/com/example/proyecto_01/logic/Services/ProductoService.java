package com.example.proyecto_01.logic.Services;


import com.example.proyecto_01.logic.Productos;
import com.example.proyecto_01.data.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Productos saveProducto(Productos producto) {
        return productoRepository.save(producto);
    }

    public List<Productos> findAllProductos() {
        return productoRepository.findAll();
    }
}
