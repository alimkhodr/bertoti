package br.com.escola.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;
import br.com.escola.api.model.Estudante;


@RestController
@RequestMapping("/estudante")
@CrossOrigin(origins = "http://127.0.0.1:8080")
public class EstudanteController {
    private List<Estudante> estudantes = new ArrayList<>();

    public EstudanteController() {
        estudantes.add(new Estudante("Ali"));
        estudantes.add(new Estudante("Bia"));
    }

    @GetMapping
    public Iterable<Estudante> getEstudantes() {
        return estudantes;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudante> getEstudanteById(@PathVariable String id) {
        for (Estudante e : estudantes) {
            if (e.getId().equals(id)) {
                return new ResponseEntity<>(e, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Estudante> postEstudante(@RequestBody Estudante estudante) {
        if (estudante.getName() == null || estudante.getName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        estudante.setId(UUID.randomUUID().toString());
        estudantes.add(estudante);
        return new ResponseEntity<>(estudante, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudante> putEstudante(@PathVariable String id, @RequestBody Estudante estudante) {
        int estudanteIndex = -1;

        for (Estudante e : estudantes) {
            if (e.getId().equals(id)) {
                estudanteIndex = estudantes.indexOf(e);
                estudantes.set(estudanteIndex, estudante);
            }
        }

        return (estudanteIndex == -1) ?
                new ResponseEntity<>(postEstudante(estudante).getBody(), HttpStatus.CREATED) :
                new ResponseEntity<>(estudante, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteEstudante(@PathVariable String id) {
    boolean removed = estudantes.removeIf(e -> e.getId().equals(id));
    return removed ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
}