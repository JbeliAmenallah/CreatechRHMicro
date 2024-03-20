package com.PFE.RH.Repositories;

import com.PFE.RH.Entities.JourFerie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourFerieRepository extends JpaRepository<JourFerie, Long> {
    // Add custom query methods if needed
}
