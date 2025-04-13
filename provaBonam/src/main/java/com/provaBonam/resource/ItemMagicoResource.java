package com.provaBonam.resource;

import com.provaBonam.domain.ItemMagico;
import com.provaBonam.domain.Personagem;
import com.provaBonam.service.ItemMagicoService;
import com.provaBonam.service.PersonagemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/itens-magicos")
@RequiredArgsConstructor
public class ItemMagicoResource {

    private final ItemMagicoService service;

    @GetMapping
    public ResponseEntity<List<ItemMagico>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemMagico> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/findAmuletoOfPersonagem/{idPersonagem}")
    public ResponseEntity<ItemMagico> findAmuletoOfPersonagem(@PathVariable(value = "idPersonagem") Long id) {
        return ResponseEntity.ok(service.findAmuletoOfPersonagem(id));
    }

    @GetMapping("/findItemsByPersonagem/{idPersonagem}")
    public ResponseEntity<List<ItemMagico>> findItemsByPersonagem(@PathVariable(value = "idPersonagem") Long id) {
        return ResponseEntity.ok(service.findItemsByPersonagem(id));
    }

    @PostMapping
    public ResponseEntity<ItemMagico> create(@RequestBody @Valid ItemMagico itemMagico) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(itemMagico));
    }

    @PutMapping("/addItemOnPersonagem/item/{idItem}/personagem/{idPersonagem}")
    public ResponseEntity<Void> addItemOnPersonagem(@PathVariable(value = "idItem") Long idItem, @PathVariable(value = "idPersonagem") Long idPersonagem) {

        service.addItemOnPersonagem(idItem, idPersonagem);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/removeItemFromPersonagem/{idItem}")
    public ResponseEntity<Void> removeItemFromPersonagem(@PathVariable(value = "idItem") Long id) {

        service.removeItemFromPersonagem(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable(value = "id") Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
