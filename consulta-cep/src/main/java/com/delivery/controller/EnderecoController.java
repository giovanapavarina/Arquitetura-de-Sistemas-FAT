package com.delivery.controller;

import com.delivery.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/cep")
public class EnderecoController {

    private static final String VIACEP_URL = "https://viacep.com.br/ws/{cep}/json/";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{cep}")
    public ResponseEntity<?> buscar(@PathVariable String cep) {

        // 1. Limpa o CEP: aceita "01001-000" ou "01001000"
        String cepLimpo = cep.replaceAll("\\D", "");

        // 2. Valida: CEP precisa ter exatamente 8 dígitos
        if (cepLimpo.length() != 8) {
            return ResponseEntity.badRequest()
                    .body(Map.of("mensagem", "CEP inválido: informe 8 dígitos"));
        }

        // 3. Consulta o ViaCEP e já converte o JSON para a classe Endereco
        Endereco endereco;
        try {
            endereco = restTemplate.getForObject(VIACEP_URL, Endereco.class, cepLimpo);
        } catch (RestClientException e) {
            return ResponseEntity.status(502)
                    .body(Map.of("mensagem", "Não foi possível consultar o ViaCEP"));
        }

        // 4. CEP com formato certo, mas que não existe
        if (endereco == null || endereco.getErro() != null) {
            return ResponseEntity.status(404)
                    .body(Map.of("mensagem", "CEP não encontrado: " + cepLimpo));
        }

        return ResponseEntity.ok(endereco);
    }
}
