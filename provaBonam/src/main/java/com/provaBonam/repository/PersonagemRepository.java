package com.provaBonam.repository;

import com.provaBonam.domain.ItemMagico;
import com.provaBonam.domain.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
}
