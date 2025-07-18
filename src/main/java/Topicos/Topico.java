package com.seuprojeto.forum.domain.topico;

import com.seuprojeto.forum.domain.autor.Autor;
import com.seuprojeto.forum.domain.curso.Curso;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos", uniqueConstraints = @UniqueConstraint(columnNames = {"titulo", "mensagem"}))
public class Topico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String titulo;

    @NotNull
    private String mensagem;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NAO_RESPONDIDO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    // Getters, setters, construtores omitidos para brevidade
}


