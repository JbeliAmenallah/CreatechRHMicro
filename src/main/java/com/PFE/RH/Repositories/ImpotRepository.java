package com.PFE.RH.Repositories;

import com.PFE.RH.Entities.Impot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpotRepository extends JpaRepository<Impot, Long> {
}
