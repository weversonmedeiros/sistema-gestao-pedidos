package com.weverson.gestao_pedidos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = GestaoPedidosApplication.class)
@AutoConfigureMockMvc
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PedidoRepository pedidoRepository;

    @BeforeEach
    public void setup() {
        // Limpa o banco e insere um pedido de teste conhecido
        pedidoRepository.deleteAll();
        Pedido pedido = new Pedido(null, "Weverson", 150.00);
        pedidoRepository.save(pedido);
    }

    @Test
    public void deveRetornarStatus200EListaComUmPedido() throws Exception {
        mockMvc.perform(get("/pedidos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].cliente").value("Weverson"))
                .andExpect(jsonPath("$[0].valorTotal").value(150.00));
    }
}