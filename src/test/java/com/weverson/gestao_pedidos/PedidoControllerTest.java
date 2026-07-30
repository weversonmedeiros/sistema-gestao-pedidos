package com.weverson.gestao_pedidos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = GestaoPedidosApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PedidoRepository pedidoRepository;

    @BeforeEach
    public void setup() {
        pedidoRepository.deleteAll();
    }

    @Test
    public void deveRetornarStatus200EListaComUmPedido() throws Exception {
        Pedido pedido = new Pedido(null, "Weverson", 150.00);
        pedidoRepository.save(pedido);

        mockMvc.perform(get("/pedidos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].cliente").value("Weverson"))
                .andExpect(jsonPath("$[0].valorTotal").value(150.00));
    }

    @Test
    public void deveCriarNovoPedidoERetornarStatus201() throws Exception {
        String jsonPedido = """
            {
                "cliente": "Maria",
                "valorTotal": 250.50
            }
            """;

        mockMvc.perform(post("/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPedido))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.cliente").value("Maria"))
                .andExpect(jsonPath("$.valorTotal").value(250.50));
    }

    @Test
    public void deveRetornarStatus400QuandoPedidoForInvalido() throws Exception {
        String jsonInvalido = """
            {
                "cliente": "",
                "valorTotal": -10.00
            }
            """;

        mockMvc.perform(post("/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInvalido))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveRetornarPedidoPorIdQuandoExistir() throws Exception {
        Pedido pedidoSalvo = pedidoRepository.save(new Pedido(null, "Carlos", 300.00));

        mockMvc.perform(get("/pedidos/" + pedidoSalvo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pedidoSalvo.getId()))
                .andExpect(jsonPath("$.cliente").value("Carlos"))
                .andExpect(jsonPath("$.valorTotal").value(300.00));
    }

    @Test
    public void deveRetornarStatus404QuandoPedidoNaoExistir() throws Exception {
        mockMvc.perform(get("/pedidos/999"))
                .andExpect(status().isNotFound());
    }
}