package com.PFE.RH.Controllers;

import com.PFE.RH.DTO.AnneeDTO;
import com.PFE.RH.Entities.Annee;
import com.PFE.RH.Mappers.AnneeMapper;
import com.PFE.RH.Services.AnneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annees")
public class AnneeController {

    @Autowired
    private AnneeService anneeService;
    @Autowired
    private AnneeMapper anneeMapper;
    @GetMapping
    public ResponseEntity<List<AnneeDTO>> getAllAnnees() {
        List<AnneeDTO> allAnnees = anneeService.getAllAnnees();
        return ResponseEntity.ok(allAnnees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnneeDTO> getAnneeById(@PathVariable Long id) {
        Annee annee = anneeService.getAnneeById(id);
        AnneeDTO anneeDTO = anneeMapper.toAnneeDTO(annee);
        return ResponseEntity.ok(anneeDTO);
    }


    @PostMapping
    public ResponseEntity<AnneeDTO> createAnnee(@RequestBody AnneeDTO anneeDTO) {
        AnneeDTO createdAnnee = anneeService.saveAnnee(anneeDTO);
        return new ResponseEntity<>(createdAnnee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnneeDTO> updateAnnee(@PathVariable Long id, @RequestBody AnneeDTO anneeDTO) {
        AnneeDTO updatedAnnee = anneeService.updateAnnee(id, anneeDTO);
        return ResponseEntity.ok(updatedAnnee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnee(@PathVariable Long id) {
        boolean deleted = anneeService.deleteAnnee(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
