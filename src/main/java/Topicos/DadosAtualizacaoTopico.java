package com.seuprojeto.forum.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO usado para atualizar os dados de um tópico existente.
 * Contém os campos obrigatórios: título e mensagem.
 */
public record DadosAtualizacaoTopico(
        @NotBlank(message = "O título não pode estar em branco") String titulo,
        @NotBlank(message = "A mensagem não pode estar em branco") String mensagem
) {
}


