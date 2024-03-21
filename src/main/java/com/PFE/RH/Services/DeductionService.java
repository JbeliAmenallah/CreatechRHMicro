package com.PFE.RH.Services;

import com.PFE.RH.DTO.DeductionDTO;
import com.PFE.RH.Entities.Deduction;
import com.PFE.RH.Mappers.DeductionMapper;
import com.PFE.RH.Repositories.DeductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeductionService {

    @Autowired
    private DeductionRepository deductionRepository;

    public List<DeductionDTO> getAllDeductions() {
        List<Deduction> deductions = deductionRepository.findAll();
        return deductions.stream()
                .map(DeductionMapper.INSTANCE::toDeductionDTO)
                .collect(Collectors.toList());
    }

    public DeductionDTO getDeductionById(Long id) {
        Deduction deduction = deductionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deduction not found with id: " + id));
        return DeductionMapper.INSTANCE.toDeductionDTO(deduction);
    }

    public DeductionDTO saveDeduction(DeductionDTO deductionDTO) {
        Deduction deduction = DeductionMapper.INSTANCE.toDeduction(deductionDTO);
        Deduction savedDeduction = deductionRepository.save(deduction);
        return DeductionMapper.INSTANCE.toDeductionDTO(savedDeduction);
    }

    public DeductionDTO updateDeduction(Long id, DeductionDTO updatedDeductionDTO) {
        Optional<Deduction> optionalDeduction = deductionRepository.findById(id);
        if (optionalDeduction.isPresent()) {
            Deduction deduction = optionalDeduction.get();
            deduction.setSalaire(updatedDeductionDTO.getSalaire());
            deduction.setMontant(updatedDeductionDTO.getMontant());
            deduction.setNombre(updatedDeductionDTO.getNombre());

            Deduction updatedDeduction = deductionRepository.save(deduction);
            return DeductionMapper.INSTANCE.toDeductionDTO(updatedDeduction);
        } else {
            throw new RuntimeException("Deduction not found with id: " + id);
        }
    }

    public DeductionDTO patchDeduction(Long id, DeductionDTO patchedDeductionDTO) {
        Optional<Deduction> optionalDeduction = deductionRepository.findById(id);
        if (optionalDeduction.isPresent()) {
            Deduction deduction = optionalDeduction.get();
            if (patchedDeductionDTO.getSalaire() != 0) {
                deduction.setSalaire(patchedDeductionDTO.getSalaire());
            }
            if (patchedDeductionDTO.getMontant() != 0) {
                deduction.setMontant(patchedDeductionDTO.getMontant());
            }
            if (patchedDeductionDTO.getNombre() != 0) {
                deduction.setNombre(patchedDeductionDTO.getNombre());
            }

            Deduction patchedDeduction = deductionRepository.save(deduction);
            return DeductionMapper.INSTANCE.toDeductionDTO(patchedDeduction);
        } else {
            throw new RuntimeException("Deduction not found with id: " + id);
        }
    }

    public boolean deleteDeduction(Long id) {
        if (deductionRepository.existsById(id)) {
            deductionRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
