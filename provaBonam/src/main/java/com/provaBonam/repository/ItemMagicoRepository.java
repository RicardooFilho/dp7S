package com.provaBonam.repository;

import com.provaBonam.domain.ItemMagico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemMagicoRepository extends JpaRepository<ItemMagico, Long> {

    @Query("""
            SELECT im
            FROM ItemMagico im
            WHERE im.personagem.id = :idPersonagem
            AND im.tipoItem = com.provaBonam.enums.TipoItem.AMULETO
            """)
    Optional<ItemMagico> findAmuletoOfPersonagem(@Param("idPersonagem") Long idPersonagem);
}
