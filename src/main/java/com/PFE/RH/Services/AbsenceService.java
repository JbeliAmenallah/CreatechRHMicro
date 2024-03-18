package com.PFE.RH.Services;

import com.PFE.RH.DTO.AbsenceDTO;
import com.PFE.RH.Entities.Absence;
import com.PFE.RH.Mappers.AbsenceMapper;
import com.PFE.RH.Repositories.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
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

    public List<AbsenceDTO> getAbsencesByMonth(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
        List<Absence> absences = absenceRepository.findByDateOfAbsenceBetween(startDate, endDate);

        if (absences.isEmpty()) {
            String message = "No absences found for the specified year and month.";
            AbsenceDTO absenceDTO = new AbsenceDTO();
            absenceDTO.setMessage(message);
            return Collections.singletonList(absenceDTO);
        }

        return absences.stream()
                .map(absenceMapper::absenceToAbsenceDTO)
                .collect(Collectors.toList());
    }

    public AbsenceDTO updateAbsence(Long id, AbsenceDTO updatedAbsenceDTO) {
        Optional<Absence> optionalAbsence = absenceRepository.findById(id);
        if (optionalAbsence.isPresent()) {
            Absence existingAbsence = optionalAbsence.get();
            existingAbsence.setDateOfAbsence(updatedAbsenceDTO.getDateOfAbsence());
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
}
