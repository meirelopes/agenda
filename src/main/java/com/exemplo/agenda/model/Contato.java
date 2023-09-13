package com.exemplo.agenda.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "contato")
@Data
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "endereco_id") // Nome da coluna de chave estrangeira
    private Endereco endereco;

}
