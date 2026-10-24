package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Buscar cliente pelo e-mail (Optional = pode ou não encontrar)
    Optional<Cliente> findByEmail(String email);

    // Buscar clientes ativos
    List<Cliente> findByAtivoTrue();

    // Buscar por parte do nome, ignorando maiúsculas/minúsculas
    List<Cliente> findByNomeContainingIgnoreCase(String nome);

}
