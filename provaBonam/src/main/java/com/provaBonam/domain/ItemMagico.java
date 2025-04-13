package com.provaBonam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.provaBonam.enums.TipoItem;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_magico")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
public class ItemMagico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_item")
    private TipoItem tipoItem;

    private Long forca;

    private Long defesa;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_personagem")
    private Personagem personagem;
}
