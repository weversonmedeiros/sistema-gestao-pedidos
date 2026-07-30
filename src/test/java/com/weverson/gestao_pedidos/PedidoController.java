package com.weverson.gestao_pedidos;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PedidoController {

    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("/pedidos")
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    @PostMapping("/pedidos")
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criarPedido(@Valid @RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    // --- NOVA ROTA ADICIONADA AQUI ---
    @GetMapping("/pedidos/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .map(pedido -> ResponseEntity.ok(pedido)) // Retorna 200 OK se encontrar
                .orElse(ResponseEntity.notFound().build()); // Retorna 404 Not Found se não achar
    }
}