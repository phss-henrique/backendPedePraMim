package com.example.pede_pra_mim.post;

/*
public record PostResponseDTO(Long id_servicos, String titulo, String descricao, String preco,
                              String cidadeServico, String imagemCaminho) {
    public PostResponseDTO(Post post){
        this(post.getId_servicos(), post.getTitulo(), post.getDescricao(),
                post.getPreco(), post.getCidadeServico(), post.getImagemCaminho());
    }
}
*/


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDTO {
    private Long id_servicos;
    private String titulo;
    private String descricao;
    private String preco;
    private String cidadeServico;
    private String imagemCaminho;

    public PostResponseDTO(Post post) {
        this.id_servicos = post.getId_servicos();
        this.titulo = post.getTitulo();
        this.descricao = post.getDescricao();
        this.preco = post.getPreco();
        this.cidadeServico = post.getCidadeServico();
        this.imagemCaminho = post.getImagemCaminho();
    }
}
