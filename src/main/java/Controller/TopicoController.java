package com.seuprojeto.forum.controller;

import com.seuprojeto.forum.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @GetMapping
    public Page<DadosListagemTopico> listar(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer ano,
            @PageableDefault(size = 10, sort = "dataCriacao", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Topico> topicos;

        if (curso != null && ano != null) {
            topicos = topicoRepository.findByCursoNomeContainingIgnoreCaseAndAno(curso, ano, pageable);
        } else if (curso != null) {
            topicos = topicoRepository.findByCursoNomeContainingIgnoreCase(curso, pageable);
        } else if (ano != null) {
            topicos = topicoRepository.findByAno(ano, pageable);
        } else {
            topicos = topicoRepository.findAll(pageable);
        }

        return topicos.map(t -> new DadosListagemTopico(
                t.getTitulo(),
                t.getMensagem(),
                t.getDataCriacao(),
                t.getStatus(),
                t.getAutor().getNome(),
                t.getCurso().getNome()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> detalhar(@PathVariable Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado"));

        var dto = new DadosDetalhamentoTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome()
        );

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        var topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var topico = topicoOptional.get();

        boolean duplicado = topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem());
        if (duplicado && !(topico.getTitulo().equals(dados.titulo()) && topico.getMensagem().equals(dados.mensagem()))) {
            return ResponseEntity.badRequest().body("Já existe um tópico com esse título e mensagem.");
        }

        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());

        return ResponseEntity.ok(new DadosDetalhamentoTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        var topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}




