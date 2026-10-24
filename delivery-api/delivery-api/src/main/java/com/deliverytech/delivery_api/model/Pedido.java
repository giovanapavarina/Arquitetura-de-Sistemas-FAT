package com.deliverytech.delivery_api.model;
 
import com.deliverytech.delivery_api.enums.StatusPedido;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroPedido;
    private LocalDateTime dataPedido;
    private Boolean entrega;
    private BigDecimal subtotal;
    private BigDecimal taxaEntrega;
    private BigDecimal valorTotal;

    // Salva o nome do enum no banco (ex.: "PENDENTE") em vez de um número
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    // To do: poderia adicionar campo observação do pedido

    /* implementação de campo de forma de pagamento
    (ENUM: DINHEIRO, CARTAO_CREDITO, CARTAO_DEBITO, PIX, OUTROS )
    */

    // Relacionamento com Cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Relacionamento com Restaurante (o Restaurante usa mappedBy = "restaurante")
    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    // Itens do pedido (o ItemPedido usa mappedBy = "pedido")
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;
}
