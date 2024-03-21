package com.PFE.RH.Repositories;

import com.PFE.RH.Entities.Annee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnneeRepository extends JpaRepository<Annee,Long> {
    Optional<Annee> findByLibele(String libele);
}
