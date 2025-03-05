package com.example.pede_pra_mim.servicos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "servico")
@Entity(name = "servicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicos")
    private Long id;

    private String titulo;  // Adicionando o título
    private String descricao;  // Renomeando de descricaoServico para descricao
    private Double preco;  // Adicionando o preço
    private String cidadeServico;  // Adicionando a cidade do serviço
    //private String status;  // Valores possíveis: pendente, aceito, recusado

    /*  public void setStatus(String status){
        this.status = status;
    }
     */
}
