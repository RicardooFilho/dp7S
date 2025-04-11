package com.provaBonam.domain;

import com.provaBonam.enums.Classe;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
@Getter
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "nome_aventureiro")
    private String nomeAventureiro;

    @Enumerated(EnumType.ORDINAL)
    private Classe classe;

    private Long level;

    private Long forca;

    private Long defesa;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_personagem")
    private List<ItemMagico> itens = new ArrayList<>();


}
