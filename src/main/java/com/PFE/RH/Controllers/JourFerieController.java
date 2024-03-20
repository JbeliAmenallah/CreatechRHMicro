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

    // Add other HTTP methods for update, delete, etc. if needed
}
