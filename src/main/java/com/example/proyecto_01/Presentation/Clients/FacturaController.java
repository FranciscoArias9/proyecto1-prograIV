package com.example.proyecto_01.Presentation.Clients;


import com.example.proyecto_01.logic.Facturas;
import com.example.proyecto_01.logic.Services.ClienteService;
import com.example.proyecto_01.logic.Services.FacturaService;
import com.example.proyecto_01.logic.Services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/facturas/new")
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("factura", new Facturas());
        model.addAttribute("clientes", clienteService.findAllClientes()); // Añadir la lista de clientes al modelo
        model.addAttribute("proveedores", proveedorService.findAllProveedores()); // Añadir la lista de proveedores al modelo
        return "registrar_factura";
    }

    @PostMapping("/facturas/add")
    public String registrarFactura(Facturas factura, Model model) {
        facturaService.saveFactura(factura);
        return "redirect:/facturas/new";
    }
}