package com.PFE.RH.Controllers;

import com.PFE.RH.DTO.AbsenceDTO;
import com.PFE.RH.Services.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/absences")
public class AbsenceController {

    @Autowired
    private AbsenceService absenceService;

    @PostMapping
    public AbsenceDTO addAbsence(@RequestBody AbsenceDTO absenceDTO) {
        return absenceService.saveAbsence(absenceDTO);
    }

    @GetMapping
    public List<AbsenceDTO> getAllAbsences() {
        return absenceService.getAllAbsences();
    }

    @GetMapping("/contact/{contactId}")
    public List<AbsenceDTO> getAbsencesByContactId(@PathVariable Long contactId) {
        return absenceService.getAbsencesByContactId(contactId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbsenceDTO> updateAbsence(
            @PathVariable Long id,
            @RequestBody AbsenceDTO updatedAbsenceDTO
    ) {
        AbsenceDTO updatedAbsence = absenceService.updateAbsence(id, updatedAbsenceDTO);
        if (updatedAbsence != null) {
            updatedAbsence.setMessage("Absence updated successfully.");
            return ResponseEntity.ok(updatedAbsence);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public String deleteAbsence(@PathVariable Long id) {
        boolean deleted = absenceService.deleteAbsence(id);
        if (deleted) {
            return "Absence with ID " + id + " deleted successfully.";
        } else {
            return "Absence with ID " + id + " not found.";
        }
    }
}
