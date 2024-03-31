package com.example.proyecto_01.data;

import com.example.proyecto_01.logic.Facturas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Facturas, Long> {
}