package com.PFE.RH.Controllers;

import com.PFE.RH.DTO.AutorisationDTO;
import com.PFE.RH.Services.AutorisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/autorisations")
public class AutorisationController {

    @Autowired
    private AutorisationService autorisationService;

    @GetMapping
    public ResponseEntity<List<AutorisationDTO>> getAllAutorisations() {
        List<AutorisationDTO> autorisations = autorisationService.getAllAutorisations();
        return new ResponseEntity<>(autorisations, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AutorisationDTO> saveAutorisation(@Valid @RequestBody AutorisationDTO autorisationDTO) {
        AutorisationDTO savedAutorisation = autorisationService.saveAutorisation(autorisationDTO);
        return new ResponseEntity<>(savedAutorisation, HttpStatus.CREATED);
    }

    @PutMapping("/{autorisationId}")
    public ResponseEntity<AutorisationDTO> updateAutorisation(
            @PathVariable Long autorisationId,
            @Valid @RequestBody AutorisationDTO updatedAutorisationDTO
    ) {
        AutorisationDTO updatedAutorisation = autorisationService.updateAutorisation(autorisationId, updatedAutorisationDTO);
        return new ResponseEntity<>(updatedAutorisation, HttpStatus.OK);
    }

    @PatchMapping("/{autorisationId}")
    public ResponseEntity<AutorisationDTO> patchAutorisation(
            @PathVariable Long autorisationId,
            @RequestBody AutorisationDTO updatedAutorisationDTO
    ) {
        AutorisationDTO patchedAutorisation = autorisationService.patchAutorisation(autorisationId, updatedAutorisationDTO);
        return new ResponseEntity<>(patchedAutorisation, HttpStatus.OK);
    }

    @DeleteMapping("/{autorisationId}")
    public ResponseEntity<Void> deleteAutorisation(@PathVariable Long autorisationId) {
        autorisationService.deleteAutorisation(autorisationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/contact/{contactId}")
    public ResponseEntity<List<AutorisationDTO>> getAutorisationsByContactId(@PathVariable Long contactId) {
        List<AutorisationDTO> autorisations = autorisationService.findByContactId(contactId);
        return new ResponseEntity<>(autorisations, HttpStatus.OK);
    }

    @GetMapping("/duration-more-than-two-hours")
    public ResponseEntity<List<AutorisationDTO>> getAutorisationsWithDurationMoreThanTwoHours() {
        List<AutorisationDTO> autorisations = autorisationService.findAutorisationsWithDurationMoreThanTwoHours();
        return new ResponseEntity<>(autorisations, HttpStatus.OK);
    }
}
