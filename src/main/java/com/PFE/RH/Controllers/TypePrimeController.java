package com.PFE.RH.Controllers;

import com.PFE.RH.DTO.TypePrimeDTO;
import com.PFE.RH.Services.TypePrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typeprimes")
public class TypePrimeController {

    @Autowired
    private TypePrimeService typePrimeService;

    @GetMapping
    public List<TypePrimeDTO> getAllTypePrimes() {
        return typePrimeService.getAllTypePrimes();
    }

    @PostMapping
    public TypePrimeDTO addTypePrime(@RequestBody TypePrimeDTO typePrimeDTO) {
        return typePrimeService.saveTypePrime(typePrimeDTO);
    }

    @PutMapping("/{id}")
    public TypePrimeDTO updateTypePrime(@PathVariable Long id, @RequestBody TypePrimeDTO updatedTypePrimeDTO) {
        return typePrimeService.updateTypePrime(id, updatedTypePrimeDTO);
    }
}
