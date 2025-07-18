package Topicos;

import com.seuprojeto.forum.domain.topico.StatusTopico;
import java.time.LocalDateTime;

/**
 * DTO para detalhamento completo de um tópico.
 */
public record DadosDetalhamentoTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        StatusTopico status,
        String autor,
        String curso
) {
}


