package com.example.proyecto_01.logic.Services;

import com.example.proyecto_01.data.FacturaRepository;
import com.example.proyecto_01.logic.Facturas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaService {
    private final FacturaRepository facturaRepository;

    @Autowired
    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public Facturas saveFactura(Facturas factura) {
        return facturaRepository.save(factura);
    }

    public List<Facturas> findAllFacturas() {
        return facturaRepository.findAll();
    }

    // Si necesitas buscar facturas específicas, por ejemplo, por cliente o fecha, puedes añadir esos métodos aquí.
    public Facturas findFacturaById(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    // Ejemplo de método para eliminar una factura
    public void deleteFactura(Long id) {
        facturaRepository.deleteById(id);
    }

    // Puedes añadir más lógica específica del negocio relacionada con facturas si es necesario
}
