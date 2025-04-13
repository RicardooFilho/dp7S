package com.provaBonam.domain;

import com.provaBonam.enums.Classe;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
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

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL)
    private List<ItemMagico> itens = new ArrayList<>();

    public Personagem toExibicao() {

        Long sumDefesa = getItens()
                .stream()
                .map(ItemMagico::getDefesa)
                .filter(Objects::nonNull)
                .reduce(0L, Long::sum);

        this.setDefesa(this.defesa + sumDefesa);

        Long sumForca = getItens()
                .stream()
                .map(ItemMagico::getForca)
                .filter(Objects::nonNull)
                .reduce(0L, Long::sum);

        this.setForca(this.forca + sumForca);

        return this;
    }
}
