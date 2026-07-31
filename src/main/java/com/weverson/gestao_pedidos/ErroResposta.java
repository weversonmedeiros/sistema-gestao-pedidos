package com.weverson.gestao_pedidos;

import java.time.LocalDateTime;
import java.util.Map;

public class ErroResposta {

    private LocalDateTime timestamp;
    private int status;
    private String mensagem;
    private Map<String, String> erros; // Guarda o campo -> mensagem de erro (ex: "cliente": "Não pode ser vazio")

    public ErroResposta(int status, String mensagem, Map<String, String> erros) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.mensagem = mensagem;
        this.erros = erros;
    }

    // Getters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Map<String, String> getErros() {
        return erros;
    }
}