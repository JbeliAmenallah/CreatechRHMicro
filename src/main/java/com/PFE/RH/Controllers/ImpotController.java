package com.PFE.RH.Controllers;

import com.PFE.RH.DTO.ImpotDTO;
import com.PFE.RH.Services.ImpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/impots")
public class ImpotController {

    @Autowired
    private ImpotService impotService;

    @PostMapping
    public ResponseEntity<ImpotDTO> createImpot(@RequestBody ImpotDTO impotDTO) {
        ImpotDTO createdImpot = impotService.saveImpot(impotDTO);
        return new ResponseEntity<>(createdImpot, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImpotDTO> getImpotById(@PathVariable Long id) {
        ImpotDTO impotDTO = impotService.getImpotById(id);
        return ResponseEntity.ok(impotDTO);
    }
}
