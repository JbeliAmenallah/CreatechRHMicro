package com.PFE.RH.Repositories;

import com.PFE.RH.Entities.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {

    List<Absence> findByContactContactId(Long contactId);

    List<Absence> findByDateOfAbsenceBetween(LocalDate startDate, LocalDate endDate);
}
