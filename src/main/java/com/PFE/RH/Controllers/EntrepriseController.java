package com.PFE.RH.Controllers;

import com.PFE.RH.DTO.EntrepriseDTO;
import com.PFE.RH.Services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entreprises")
public class EntrepriseController {

    private final EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @GetMapping
    public ResponseEntity<List<EntrepriseDTO>> getAllEntreprises() {
        List<EntrepriseDTO> entreprises = entrepriseService.getAllEntreprises();
        return new ResponseEntity<>(entreprises, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EntrepriseDTO> createEntreprise(@RequestBody EntrepriseDTO entrepriseDTO) {
        EntrepriseDTO createdEntreprise = entrepriseService.saveEntreprise(entrepriseDTO);
        return new ResponseEntity<>(createdEntreprise, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntrepriseDTO> updateEntreprise(@PathVariable Long id, @RequestBody EntrepriseDTO updatedEntrepriseDTO) {
        EntrepriseDTO updatedEntreprise = entrepriseService.updateEntreprise(id, updatedEntrepriseDTO);
        return new ResponseEntity<>(updatedEntreprise, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntreprise(@PathVariable Long id) {
        boolean deleted = entrepriseService.deleteEntreprise(id);
        if (deleted) {
            return new ResponseEntity<>("Entreprise deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Entreprise not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
