package com.example.proyecto_01.Presentation.Clients;

import com.example.proyecto_01.logic.Clientes;
import com.example.proyecto_01.logic.Service;
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

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        // Aquí puedes realizar la lógica de autenticación y verificar las credenciales.
        // En este ejemplo, simplemente imprimimos los valores del nombre de usuario y contraseña recibidos.
        //System.out.println("Username: " + username);
        //System.out.println("Password: " + password);
        Clientes user1 = new Clientes();

        user1.setNombre("Pepe");
        user1.setIdCliente(1111);
        user1.setUsuario(username);
        user1.setClave(password);
        myService.saveCliente(user1);
        model.addAttribute("cliente1", user1);

        // Dependiendo de la lógica de tu aplicación, puedes redirigir a diferentes páginas.
        // Por ejemplo, si la autenticación es exitosa, podrías redirigir al usuario a una página de inicio.
        // Si la autenticación falla, podrías redirigir al usuario de vuelta al formulario de inicio de sesión con un mensaje de error.
        // Aquí simplemente redirigimos al usuario nuevamente al formulario de inicio de sesión.
        return "home";
    }

}
