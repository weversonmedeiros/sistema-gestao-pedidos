package com.weverson.gestao_pedidos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PedidoController {

    private final PedidoRepository pedidoRepository;

    // Injeção de dependência via construtor (boa prática)
    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("/pedidos")
    public List listarPedidos() {
        return pedidoRepository.findAll();
    }
}