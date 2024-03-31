package com.example.proyecto_01.data;


import com.example.proyecto_01.logic.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Clientes, Long> {
}
