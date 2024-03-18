package com.PFE.RH.Controllers;

import com.PFE.RH.DTO.CongeDTO;
import com.PFE.RH.Services.CongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conges")
public class CongeController {

    @Autowired
    private CongeService congeService;

    @PostMapping
    public ResponseEntity<CongeDTO> addConge(@RequestBody CongeDTO congeDTO) {
        CongeDTO savedConge = congeService.saveConge(congeDTO);
        return new ResponseEntity<>(savedConge, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CongeDTO>> getAllConges() {
        List<CongeDTO> congeDTOList = congeService.getAllConges();
        return new ResponseEntity<>(congeDTOList, HttpStatus.OK);
    }
    @GetMapping("/{congeId}")
    public ResponseEntity<CongeDTO> getConge(@PathVariable Long congeId) {
        CongeDTO congeDTO = congeService.getCongeById(congeId);
        if (congeDTO != null) {
            return new ResponseEntity<>(congeDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{congeId}")
    public ResponseEntity<CongeDTO> updateConge(@PathVariable Long congeId, @RequestBody CongeDTO updatedCongeDTO) {
        CongeDTO updatedConge = congeService.updateConge(congeId, updatedCongeDTO);
        if (updatedConge != null) {
            return new ResponseEntity<>(updatedConge, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{congeId}")
    public ResponseEntity<Void> deleteConge(@PathVariable Long congeId) {
        boolean deleted = congeService.deleteConge(congeId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
