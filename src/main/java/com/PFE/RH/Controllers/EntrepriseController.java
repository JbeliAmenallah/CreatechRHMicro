package com.PFE.RH.Controllers;

import com.PFE.RH.DTO.EntrepriseDTO;
import com.PFE.RH.DTO.EntrepriseWithoutContactsDTO;
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
   /* public ResponseEntity<List<EntrepriseWithoutContactsDTO>> getAllEntreprises() {
        List<EntrepriseWithoutContactsDTO> entreprises = entrepriseService.getAllEntreprises();
        return new ResponseEntity<>(entreprises, HttpStatus.OK);
    }*/

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
    @PostMapping("/{entrepriseId}/addGrade/{gradeId}")
    public ResponseEntity<EntrepriseWithoutContactsDTO> addGradeToEntreprise(@PathVariable Long entrepriseId, @PathVariable Long gradeId) {
        EntrepriseWithoutContactsDTO updatedEntreprise = entrepriseService.addGradeToEntreprise(entrepriseId, gradeId);
        return new ResponseEntity<>(updatedEntreprise, HttpStatus.OK);
    }

    @PostMapping("/{entrepriseId}/addGroupe/{groupeId}")
    public ResponseEntity<EntrepriseWithoutContactsDTO> addGroupeToEntreprise(@PathVariable Long entrepriseId, @PathVariable Long groupeId) {
        EntrepriseWithoutContactsDTO updatedEntreprise = entrepriseService.addGroupeToEntreprise(entrepriseId, groupeId);
        return new ResponseEntity<>(updatedEntreprise, HttpStatus.OK);
    }

    @PostMapping("/{entrepriseId}/addCategory/{categoryId}")
    public ResponseEntity<EntrepriseWithoutContactsDTO> addCategoryToEntreprise(@PathVariable Long entrepriseId, @PathVariable Long categoryId) {
        EntrepriseWithoutContactsDTO updatedEntreprise = entrepriseService.addCategoryToEntreprise(entrepriseId, categoryId);
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
