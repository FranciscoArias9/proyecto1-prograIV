package com.example.proyecto_01.Presentation.Clients;


import com.example.proyecto_01.logic.Detalle_Factura;
import com.example.proyecto_01.logic.Facturas;
import com.example.proyecto_01.logic.Productos;
import com.example.proyecto_01.logic.Proveedores;
import com.example.proyecto_01.logic.Services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    List<Productos> listaItems = new ArrayList<>(); //lista de los productos que se van a comprar
    List<Detalle_Factura> listaDetalleFactura = new ArrayList<>();

    @GetMapping("/facturas/new")
    public String mostrarFormularioDeRegistro(Model model, HttpSession session) {

        model.addAttribute("factura", new Facturas());
        model.addAttribute("detalleFactura", new Detalle_Factura());
        model.addAttribute("clientes", clienteService.findAllClientes()); // Añadir la lista de clientes al modelo
        model.addAttribute("proveedores", proveedorService.findAllProveedores()); // Añadir la lista de proveedores al modelo
        model.addAttribute("productos", productoService.findProductosByProveedor((Proveedores) session.getAttribute("proveedor"))); //Agregar la lista de productos al modelo
        model.addAttribute("listaItems", listaItems);
        model.addAttribute("listaDetalles", listaDetalleFactura);
        return "registrar_factura";
    }

    @PostMapping("/facturas/add")
    public String registrarFactura(Facturas factura,HttpSession session, Model model) {

        factura.setProveedoresByIdProveedor((Proveedores) session.getAttribute("proveedor"));
        factura.setMonto(calcularMonto());

        facturaService.saveFactura(factura);

        for(int i = 0; i < listaItems.size(); i++){
            listaDetalleFactura.get(i).setFactura(factura);
            detalleFacturaService.saveDetalleFactura(listaDetalleFactura.get(i));
        }
        listaItems.clear();
        listaDetalleFactura.clear();

        return "redirect:/facturas/new";
    }
    double calcularMonto(){
        double monto = 0;
        for(int i = 0; i < listaItems.size(); i++){
            Double valor = (Double) listaDetalleFactura.get(i).getProducto().getValor();
            int cant = listaDetalleFactura.get(i).getCantidad();
            monto += valor*cant;
        }
        System.out.println("++++++++++++++:" + monto);
        return monto;
    }


    @PostMapping("/facturas/add_Item")
    public String registrarDetalleFactura(@ModelAttribute("productoByIdProducto") Productos producto, @RequestParam("cantidad") int cantidad, Model model) {
        System.out.println("--------------: " + producto.getNombre());
        System.out.println("--------------:" + cantidad);
        Detalle_Factura detalleFactura = new Detalle_Factura();
        detalleFactura.setCantidad(cantidad);
        detalleFactura.setProducto(producto);
        detalleFactura.setPrecioUnitario(10.0);
        listaItems.add(producto);
        listaDetalleFactura.add(detalleFactura);

        //listaDetalleFactura.add();
        //detalleFactura.setFactura(n);


        //facturaService.saveFactura(factura);
        return "redirect:/facturas/new";
    }


    @PostMapping("/aumentarCantidad")
    public String aumentarProductoDetalle(Long detalleFactura,HttpSession session, Model model) {

        for (Detalle_Factura indiceObj : listaDetalleFactura) {
            if (Objects.equals(indiceObj.getId_detalle(), detalleFactura)) {
                indiceObj.setCantidad(indiceObj.getCantidad() + 1);
                break;
            }
        }
        return "redirect:/facturas/new";
    }

    @PostMapping("/disminuirCantidad")
    public String disminuirProductoDetalle(Long detalleFactura,HttpSession session, Model model) {

        for (Detalle_Factura indiceObj : listaDetalleFactura) {
            if (Objects.equals(indiceObj.getId_detalle(), detalleFactura)) {
                indiceObj.setCantidad(indiceObj.getCantidad() - 1);
                break;
            }
        }
        return "redirect:/facturas/new";
    }

}