package com.example.proyecto_01.logic;

import com.example.proyecto_01.data.CientRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service("service")
public class Service {
    @Autowired
    CientRepository clientRepository;

    public void saveCliente(Clientes cliente) {
        clientRepository.save(cliente);
    }
}
