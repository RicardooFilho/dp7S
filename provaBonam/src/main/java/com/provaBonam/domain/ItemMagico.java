package com.provaBonam.domain;

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
public class ItemMagico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.ORDINAL)
    private TipoItem tipoItem;

    private Long forca;

    private Long defesa;
}
