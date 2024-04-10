package com.example.proyecto_01.Presentation.Clients;

import com.example.proyecto_01.logic.Proveedores;
import com.example.proyecto_01.logic.Services.ClienteService;
import com.example.proyecto_01.logic.Services.ProveedorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;


@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ClienteService clienteService;


    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model,
                        HttpSession session) {
        if(Objects.equals(username, "admin") && Objects.equals(password, "admin")){
            return "redirect:/admin";
        }
        Proveedores proveedor = proveedorService.encontrarPorUsuario(username);
        if(proveedorService.validarCredenciales(username, password) && proveedor.isEstado()){
            // Obtener el proveedor autenticado
            // Almacenar el ID del proveedor en la sesión
            session.setAttribute("proveedor", proveedor);
            session.setAttribute("usuario", proveedor.getUsuario());
            return "redirect:/facturas/new"; // Asegúrate de redirigir a una ruta válida

        } return "redirect:/index.html";

    }

    @GetMapping("/logOut")
    public String login(HttpSession session) {
        if(session!=null){
            session.invalidate();
        }
        return "redirect:/index.html";

    }

    @GetMapping("/about")
    public String about() {
        return "about";

    }





}
