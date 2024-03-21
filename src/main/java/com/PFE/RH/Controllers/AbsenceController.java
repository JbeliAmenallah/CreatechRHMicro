package com.PFE.RH.Controllers;

import com.PFE.RH.DTO.AbsenceDTO;
import com.PFE.RH.Services.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/absences")
public class AbsenceController {

    @Autowired
    private AbsenceService absenceService;

    @GetMapping
    public ResponseEntity<List<AbsenceDTO>> getAllAbsences() {
        List<AbsenceDTO> absences = absenceService.getAllAbsences();
        return new ResponseEntity<>(absences, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AbsenceDTO> addAbsence(@RequestBody AbsenceDTO absenceDTO) {
        AbsenceDTO savedAbsence = absenceService.saveAbsence(absenceDTO);
        return new ResponseEntity<>(savedAbsence, HttpStatus.CREATED);
    }

   /* @GetMapping("/{id}")
    public ResponseEntity<AbsenceDTO> getAbsenceById(@PathVariable Long id) {
        AbsenceDTO absence = absenceService.getAbsenceById(id);
        if (absence != null) {
            return new ResponseEntity<>(absence, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<AbsenceDTO> updateAbsence(@PathVariable Long id, @RequestBody AbsenceDTO updatedAbsenceDTO) {
        AbsenceDTO updatedAbsence = absenceService.updateAbsence(id, updatedAbsenceDTO);
        if (updatedAbsence != null) {
            return new ResponseEntity<>(updatedAbsence, HttpStatus.OK);
        } else {            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AbsenceDTO> patchAbsence(@PathVariable Long id, @RequestBody AbsenceDTO patchedAbsenceDTO) {
        AbsenceDTO patchedAbsence = absenceService.patchAbsence(id, patchedAbsenceDTO);
        if (patchedAbsence != null) {
            return new ResponseEntity<>(patchedAbsence, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbsence(@PathVariable Long id) {
        boolean deleted = absenceService.deleteAbsence(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-contact/{contactId}")
    public ResponseEntity<List<AbsenceDTO>> getAbsencesByContactId(@PathVariable Long contactId) {
        List<AbsenceDTO> absencesByContact = absenceService.getAbsencesByContactId(contactId);
        if (!absencesByContact.isEmpty()) {
            return new ResponseEntity<>(absencesByContact, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/between-dates")
    public ResponseEntity<List<AbsenceDTO>> getAbsencesBetweenDates(@RequestParam("startDate") LocalDate startDate,
                                                                    @RequestParam("endDate") LocalDate endDate) {
        List<AbsenceDTO> absences = absenceService.getAbsencesBetweenDates(startDate, endDate);
        if (!absences.isEmpty()) {
            return new ResponseEntity<>(absences, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
