package com.seuprojeto.forum.dto;

import com.seuprojeto.forum.domain.topico.StatusTopico;
import java.time.LocalDateTime;

public record DadosListagemTopico(
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        StatusTopico status,
        String autor,
        String curso
) {}

