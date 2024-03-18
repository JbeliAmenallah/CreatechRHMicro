package com.PFE.RH.Controllers;

import com.PFE.RH.DTO.AutorisationDTO;
import com.PFE.RH.Services.AutorisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autorisations")
public class AutorisationController {

    @Autowired
    private AutorisationService autorisationService;

    @GetMapping
    public List<AutorisationDTO> getAllAutorisations() {
        return autorisationService.getAllAutorisations();
    }

    @PostMapping
    public AutorisationDTO addAutorisation(@RequestBody AutorisationDTO autorisationDTO) {
        return autorisationService.saveAutorisation(autorisationDTO);
    }

    @PutMapping("/{autorisationId}")
    public AutorisationDTO updateAutorisation(
            @PathVariable Long autorisationId,
            @RequestBody AutorisationDTO autorisationDTO
    ) {
        return autorisationService.updateAutorisation(autorisationId, autorisationDTO);
    }

    @DeleteMapping("/{autorisationId}")
    public void deleteAutorisation(@PathVariable Long autorisationId) {
        autorisationService.deleteAutorisation(autorisationId);
    }
}
