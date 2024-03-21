package com.PFE.RH.Controllers;

import com.PFE.RH.DTO.JourFerieDTO;
import com.PFE.RH.Services.JourFerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jourferies")
public class JourFerieController {

    @Autowired
    private JourFerieService jourFerieService;

    @GetMapping
    public ResponseEntity<List<JourFerieDTO>> getAllJourFeries() {
        List<JourFerieDTO> allJourFeries = jourFerieService.getAllJourFeries();
        return ResponseEntity.ok(allJourFeries);
    }

    @PostMapping
    public ResponseEntity<JourFerieDTO> createJourFerie(@RequestBody JourFerieDTO jourFerieDTO) {
        JourFerieDTO createdJourFerie = jourFerieService.saveJourFerie(jourFerieDTO);
        return new ResponseEntity<>(createdJourFerie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JourFerieDTO> updateJourFerie(@PathVariable Long id, @RequestBody JourFerieDTO jourFerieDTO) {
        JourFerieDTO updatedJourFerie = jourFerieService.updateJourFerie(id, jourFerieDTO);
        return ResponseEntity.ok(updatedJourFerie);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<JourFerieDTO> partialUpdateJourFerie(@PathVariable Long id, @RequestBody JourFerieDTO partialJourFerieDTO) {
        JourFerieDTO updatedJourFerie = jourFerieService.partialUpdateJourFerie(id, partialJourFerieDTO);
        return ResponseEntity.ok(updatedJourFerie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJourFerie(@PathVariable Long id) {
        boolean deleted = jourFerieService.deleteJourFerie(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
