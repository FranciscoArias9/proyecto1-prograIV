package com.example.proyecto_01.Presentation.Clients;

import com.example.proyecto_01.logic.Clientes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.stereotype.Controller
public class Controller {


    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("text", "POU ES MEJOR QUE MOY");
        Clientes cliente1 = new Clientes();
        cliente1.setIdCliente(1234);
        cliente1.setNombre("RANA");
        model.addAttribute("cliente", cliente1);
        model.addAttribute("nombre", cliente1.getNombre()); //no es la idea

        return "view";
    }
}
