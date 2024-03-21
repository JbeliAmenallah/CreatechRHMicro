package com.PFE.RH.Services;

import com.PFE.RH.DTO.AbsenceDTO;
import com.PFE.RH.Entities.Absence;
import com.PFE.RH.Mappers.AbsenceMapper;
import com.PFE.RH.Repositories.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private AbsenceMapper absenceMapper;

    public List<AbsenceDTO> getAllAbsences() {
        List<Absence> absences = absenceRepository.findAll();
        return absences.stream()
                .map(absence -> {
                    AbsenceDTO absenceDTO = absenceMapper.absenceToAbsenceDTO(absence);
                    absenceDTO.setMessage(absenceDTO.generateMessage(absence.getContact().getName()));
                    return absenceDTO;
                })
                .collect(Collectors.toList());
    }

    public AbsenceDTO saveAbsence(AbsenceDTO absenceDTO) {
        Absence absence = absenceMapper.absenceDTOToAbsence(absenceDTO);
        Absence savedAbsence = absenceRepository.save(absence);
        return absenceMapper.absenceToAbsenceDTO(savedAbsence);
    }

    public List<AbsenceDTO> getAbsencesByContactId(Long contactId) {
        List<Absence> absences = absenceRepository.findByContactContactId(contactId);
        return absences.stream()
                .map(absenceMapper::absenceToAbsenceDTO)
                .collect(Collectors.toList());
    }

    public AbsenceDTO updateAbsence(Long id, AbsenceDTO updatedAbsenceDTO) {
        Optional<Absence> optionalAbsence = absenceRepository.findById(id);
        if (optionalAbsence.isPresent()) {
            Absence existingAbsence = optionalAbsence.get();
            existingAbsence.setDateDebutAbsence(Date.valueOf(updatedAbsenceDTO.getDateDebutAbsence()));
            existingAbsence.setDateFinAbsence(Date.valueOf(updatedAbsenceDTO.getDateFinAbsence()));
            existingAbsence.setReason(updatedAbsenceDTO.getReason());

            // Save the updated absence
            Absence updatedAbsence = absenceRepository.save(existingAbsence);
            return absenceMapper.absenceToAbsenceDTO(updatedAbsence);
        } else {
            updatedAbsenceDTO.setMessage("Absence not found with ID: " + id);
            return updatedAbsenceDTO;
        }
    }

    public boolean deleteAbsence(Long id) {
        Optional<Absence> optionalAbsence = absenceRepository.findById(id);
        if (optionalAbsence.isPresent()) {
            absenceRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<AbsenceDTO> getAbsencesBetweenDates(LocalDate startDate, LocalDate endDate) {
        Date sqlStartDate = Date.valueOf(startDate);
        Date sqlEndDate = Date.valueOf(endDate);
        List<Absence> absences = absenceRepository.findByDateDebutAbsenceBetween(sqlStartDate, sqlEndDate);
        return absences.stream()
                .map(absenceMapper::absenceToAbsenceDTO)
                .collect(Collectors.toList());
    }

    public AbsenceDTO patchAbsence(Long id, AbsenceDTO patchedAbsenceDTO) {
        Optional<Absence> optionalAbsence = absenceRepository.findById(id);
        if (optionalAbsence.isPresent()) {
            Absence absence = optionalAbsence.get();

            // Patch the fields if they are not null
            if (patchedAbsenceDTO.getDateDebutAbsence() != null) {
                absence.setDateDebutAbsence(Date.valueOf(patchedAbsenceDTO.getDateDebutAbsence()));
            }
            if (patchedAbsenceDTO.getDateFinAbsence() != null) {
                absence.setDateFinAbsence(Date.valueOf(patchedAbsenceDTO.getDateFinAbsence()));
            }
            if (patchedAbsenceDTO.getReason() != null) {
                absence.setReason(patchedAbsenceDTO.getReason());
            }

            Absence patchedAbsence = absenceRepository.save(absence);
            return absenceMapper.absenceToAbsenceDTO(patchedAbsence);
        } else {
            // Absence not found, return null or throw an exception
            return null;
        }
    }


}
