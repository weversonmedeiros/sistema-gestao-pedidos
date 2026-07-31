package com.weverson.gestao_pedidos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Capta falhas de validação do @Valid (ex: campos vazios, valores inválidos)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> tratarErroValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();

        // Mapeia cada campo que falhou com sua respectiva mensagem
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            erros.put(error.getField(), error.getDefaultMessage());
        }

        ErroResposta resposta = new ErroResposta(
            HttpStatus.BAD_REQUEST.value(),
            "Erro de validação nos campos informados",
            erros
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }
}