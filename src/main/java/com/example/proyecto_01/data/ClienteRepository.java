package com.example.proyecto_01.data;


import com.example.proyecto_01.logic.Clientes;
import com.example.proyecto_01.logic.Proveedores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Clientes, Long> {
    List<Clientes> findByProveedoresByIdProveedor(Proveedores proveedor);
}
