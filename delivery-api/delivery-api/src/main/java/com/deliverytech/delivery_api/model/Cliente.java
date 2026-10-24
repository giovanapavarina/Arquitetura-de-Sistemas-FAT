package com.deliverytech.delivery_api.model;
 
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
public class Cliente {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private boolean ativo;

 /* 
 TO DO: Adicionar Data em que o cliente foi cadastrado no sistema
 private LocalDateTime dataCadastro - visto que pode ter mais de um usuário realizando essa ação ao mesmo tempo.
 REVER UUIDV4, UUID V7, SNOWFLAKE ... 
 */ 

   @JsonIgnore
   @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
   private List<Pedido> pedidos;
}
