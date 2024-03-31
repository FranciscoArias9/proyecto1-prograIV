package com.example.proyecto_01.data;



import com.example.proyecto_01.logic.Proveedores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedores, Long> {
    Proveedores findByUsuario(String user);
}
