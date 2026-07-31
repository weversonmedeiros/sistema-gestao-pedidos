package com.weverson.gestao_pedidos;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido criar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public boolean deletar(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Trocamos o .map() pelo if/else tradicional para evitar o erro de Cast
    public Optional<Pedido> atualizar(Long id, Pedido dadosAtualizados) {
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);
        
        if (pedidoExistente.isPresent()) {
            Pedido pedido = pedidoExistente.get();
            pedido.setCliente(dadosAtualizados.getCliente());
            pedido.setValorTotal(dadosAtualizados.getValorTotal());
            return Optional.of(pedidoRepository.save(pedido));
        }
        
        return Optional.empty();
    }
}