package com.example.proyecto_01.Presentation.Clients;

import com.example.proyecto_01.logic.Clientes;
import com.example.proyecto_01.logic.Proveedores;
import com.example.proyecto_01.logic.Service;
import com.example.proyecto_01.logic.Services.ClienteService;
import com.example.proyecto_01.logic.Services.ProveedorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    Service myService;
    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ClienteService clienteService;


    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model,
                        HttpSession session) {
        // Aquí puedes realizar la lógica de autenticación y verificar las credenciales.
        // En este ejemplo, simplemente imprimimos los valores del nombre de usuario y contraseña recibidos.
        //System.out.println("Username: " + username);
        //System.out.println("Password: " + password);
        if(proveedorService.validarCredenciales(username, password)){
            // Obtener el proveedor autenticado
            Proveedores proveedor = proveedorService.encontrarPorUsuario(username);
            // Almacenar el ID del proveedor en la sesión
            session.setAttribute("proveedor", proveedor);
            session.setAttribute("usuario", proveedor.getUsuario());
            return "home";
        } return "error";

    }



}
