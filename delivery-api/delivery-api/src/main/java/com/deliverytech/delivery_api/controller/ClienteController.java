package com.deliverytech.delivery_api.controller;
 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import java.util.ArrayList;
import java.util.List;
 
/*
Definimos através da annotation de que esse arquivo trabalha com
requisições HTTP/HTTPS
*/
@RestController
public class ClienteController {

    /*
    A entidade Cliente (pasta model) agora é uma tabela do banco e não tem mais
    o construtor (nome, sobrenome). Para manter os exemplos desta aula,
    usamos um record simples só para a resposta da API.
    */
    public record ClienteResumo(String nome, String sobrenome) {}
   
    /*Simular um banco de dados com uma lista de Cliente
    construir utilizando Spring Boot REST delivery_api
    através do nosso localhost:8080/cliente
    */
   @GetMapping("/cliente")
   public ClienteResumo getCliente() {
    return new ClienteResumo("João", "Silva");
   }
 
    /* Simulando um banco de dados com uma lista de clientes
        Retornando uma List
        http://localhost:8080/clientes
    */
    @GetMapping("/clientes")
    /*<List> forma como usamos para percorer algo em uma lista (Array) */
    public List<ClienteResumo> getClientes() {
        List<ClienteResumo> clientes = new ArrayList<>();
        clientes.add(new ClienteResumo("João", "Silva"));
        clientes.add(new ClienteResumo("Maria", "Souza"));
        clientes.add(new ClienteResumo("Pedro", "Oliveira"));
        return clientes;
    }
 
    /* lembrando se for salvo com caracter especial no caso o ã a forma de busca e diferente */
    // O valor na URL diretamente injetado no método
    // http://localhost:8080/cliente/Jo%C3%A3o/Silva
    @GetMapping("/cliente/{nome}/{sobrenome}")
    public ClienteResumo getClienteByPathVariable(@PathVariable String nome, @PathVariable String sobrenome) {
        return new ClienteResumo(nome, sobrenome);
    }
 
    // build onde os valores são passados como query params
    // URL: http://localhost:8080/cliente/query?nome=Jo%C3%A3o&sobrenome=Silva
    @GetMapping("/cliente/query")
    public ClienteResumo getClienteByQueryParam(
        @RequestParam String nome, @RequestParam String sobrenome) {
        return new ClienteResumo(nome, sobrenome);
    }
 
}
