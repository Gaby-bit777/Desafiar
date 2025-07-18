package Topicos;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO utilizado para o cadastro de novos tópicos.
 * Todos os campos são obrigatórios.
 */
public record DadosCadastroTopico(

        @NotBlank(message = "O título não pode estar em branco") String titulo,

        @NotBlank(message = "A mensagem não pode estar em branco") String mensagem,

        @NotBlank(message = "O nome do autor não pode estar em branco") String nomeAutor,

        @NotBlank(message = "O nome do curso não pode estar em branco") String nomeCurso

) {
}


