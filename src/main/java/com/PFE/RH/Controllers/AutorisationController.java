package com.PFE.RH.Controllers;

import com.PFE.RH.DTO.AutorisationDTO;
import com.PFE.RH.Services.AutorisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/autorisations")
public class AutorisationController {

    @Autowired
    private AutorisationService autorisationService;

    @GetMapping
    public ResponseEntity<List<AutorisationDTO>> getAllAutorisations() {
        List<AutorisationDTO> autorisations = autorisationService.getAllAutorisations();
        return new ResponseEntity<>(autorisations, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AutorisationDTO> addAutorisation(@Valid @RequestBody AutorisationDTO autorisationDTO) {
        AutorisationDTO createdAutorisation = autorisationService.saveAutorisation(autorisationDTO);
        return new ResponseEntity<>(createdAutorisation, HttpStatus.CREATED);
    }

    @PutMapping("/{autorisationId}")
    public ResponseEntity<AutorisationDTO> updateAutorisation(
            @PathVariable Long autorisationId,
            @Valid @RequestBody AutorisationDTO autorisationDTO
    ) {
        AutorisationDTO updatedAutorisation = autorisationService.updateAutorisation(autorisationId, autorisationDTO);
        return new ResponseEntity<>(updatedAutorisation, HttpStatus.OK);
    }

    @DeleteMapping("/{autorisationId}")
    public ResponseEntity<String> deleteAutorisation(@PathVariable Long autorisationId) {
        autorisationService.deleteAutorisation(autorisationId);
        return new ResponseEntity<>("Autorisation with ID " + autorisationId + " deleted successfully.", HttpStatus.OK);
    }
}
