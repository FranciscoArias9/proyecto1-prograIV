package com.example.proyecto_01.data;



import com.example.proyecto_01.logic.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Productos, Long> {
}
