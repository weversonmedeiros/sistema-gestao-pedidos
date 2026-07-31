package com.weverson.gestao_pedidos;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Gerenciamento do ciclo de vida dos pedidos") // Dá um título bonito para a sessão
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os pedidos", description = "Retorna uma lista de todos os pedidos cadastrados no banco.")
    public List listarPedidos() {
        return pedidoService.listarTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar um novo pedido", description = "Cadastra um novo pedido e retorna os dados gerados com o ID.")
    @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso")
    public Pedido criarPedido(@Valid @RequestBody Pedido pedido) {
        return pedidoService.criar(pedido);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido por ID", description = "Retorna os detalhes de um pedido específico. Se não existir, retorna erro 404.")
    public ResponseEntity buscarPedidoPorId(@PathVariable Long id) {
        Optional pedido = pedidoService.buscarPorId(id);
        
        if (pedido.isPresent()) {
            return ResponseEntity.ok(pedido.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um pedido", description = "Remove permanentemente um pedido do sistema pelo seu ID.")
    public ResponseEntity deletarPedido(@PathVariable Long id) {
        if (pedidoService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um pedido", description = "Atualiza os dados (cliente e valor total) de um pedido já existente.")
    public ResponseEntity atualizarPedido(@PathVariable Long id, @Valid @RequestBody Pedido dados) {
        Optional pedidoAtualizado = pedidoService.atualizar(id, dados);
        
        if (pedidoAtualizado.isPresent()) {
            return ResponseEntity.ok(pedidoAtualizado.get());
        }
        return ResponseEntity.notFound().build();
    }
}