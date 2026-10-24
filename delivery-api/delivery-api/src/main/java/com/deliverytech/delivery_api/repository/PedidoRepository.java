package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.enums.StatusPedido;
import com.deliverytech.delivery_api.model.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Pedidos de um cliente
    List<Pedido> findByClienteId(Long clienteId);

    // Pedidos por status
    List<Pedido> findByStatus(StatusPedido status);

    // Pedidos feitos entre duas datas
    List<Pedido> findByDataPedidoBetween(LocalDateTime inicio, LocalDateTime fim);

}
