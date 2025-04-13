package com.provaBonam.resource;

import com.provaBonam.domain.Personagem;
import com.provaBonam.repository.PersonagemRepository;
import com.provaBonam.service.PersonagemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/personagens")
@RequiredArgsConstructor
public class PersonagemResource {

    private final PersonagemService service;

    @GetMapping
    public ResponseEntity<List<Personagem>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personagem> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Personagem> create(@RequestBody @Valid Personagem personagem) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(personagem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personagem> update(@PathVariable(value = "id") Long id, @RequestParam String newNomeAventureiro) {
        return ResponseEntity.ok(service.update(id, newNomeAventureiro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
