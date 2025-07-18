package Topicos;

import com.seuprojeto.forum.domain.topico.StatusTopico;
import java.time.LocalDateTime;

/**
 * DTO para listagem resumida de tópicos.
 */
public record DadosListagemTopico(
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        StatusTopico status,
        String autor,
        String curso
) {
}


