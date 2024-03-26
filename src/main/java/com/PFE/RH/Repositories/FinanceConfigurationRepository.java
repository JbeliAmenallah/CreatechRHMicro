package com.PFE.RH.Repositories;

import com.PFE.RH.Entities.FinanceConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FinanceConfigurationRepository extends JpaRepository<FinanceConfiguration,Long> {
    Optional<FinanceConfiguration> findByLibeleAndYear(String libele, int year);
}
