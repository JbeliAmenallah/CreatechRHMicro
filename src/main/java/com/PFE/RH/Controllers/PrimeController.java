package com.PFE.RH.Controllers;

import com.PFE.RH.DTO.PrimeDTO;
import com.PFE.RH.Services.PrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/primes")
public class PrimeController {

    @Autowired
    private PrimeService primeService;

    @PostMapping
    public PrimeDTO addPrime(@RequestBody PrimeDTO primeDTO) {
        return primeService.savePrime(primeDTO);
    }

    @GetMapping
    public List<PrimeDTO> getAllPrimes() {
        return primeService.getAllPrimes();
    }

    @GetMapping("/{id}")
    public PrimeDTO getPrimeById(@PathVariable Long id) {
        return primeService.getPrimeById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deletePrime(@PathVariable Long id) {
        return primeService.deletePrime(id);
    }
}
