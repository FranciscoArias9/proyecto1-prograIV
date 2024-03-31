package com.example.proyecto_01.logic.Services;



import com.example.proyecto_01.logic.Clientes;
import com.example.proyecto_01.data.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Clientes saveCliente(Clientes cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Clientes> findAllClientes() {
        return clienteRepository.findAll();
    }
}
