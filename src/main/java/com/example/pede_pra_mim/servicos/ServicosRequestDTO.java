package com.example.pede_pra_mim.servicos;

public record ServicosRequestDTO(
        String titulo,
        String descricao,
        Double preco,
        String cidadeServico
) {}
