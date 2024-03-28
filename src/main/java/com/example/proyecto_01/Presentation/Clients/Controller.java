package com.example.proyecto_01.Presentation.Clients;

import com.example.proyecto_01.logic.Clientes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@org.springframework.stereotype.Controller
public class Controller {


    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("text", "POU ES MEJOR QUE MOY");
        Clientes cliente1 = new Clientes();
        cliente1.setIdCliente(1234);
        cliente1.setNombre("RANA");
        cliente1.setClave("1111");
        model.addAttribute("cliente", cliente1);
        model.addAttribute("nombre", cliente1.getNombre()); //no es la idea

        return "view";
    }

    @GetMapping("/list")
    public String list(Model model){
        List<Clientes> list = new ArrayList<>();
        Clientes cl1 = new Clientes();
        cl1.setNombre("Pepe");
        cl1.setIdCliente(1234);
        list.add(cl1);
        Clientes cl2 = new Clientes();
        cl2.setNombre("Terry");
        cl2.setIdCliente(4564);
        list.add(cl2);
        model.addAttribute("clients", list);
        model.addAttribute("title", "my title");
        return "list";
    }
}
