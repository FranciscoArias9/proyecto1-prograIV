package com.example.proyecto_01.data;

import com.example.proyecto_01.logic.Clientes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CientRepository extends CrudRepository<Clientes, String> {

    // Método para guardar un cliente en la base de datos
    Clientes save(Clientes cliente);
}

