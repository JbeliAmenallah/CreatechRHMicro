package com.PFE.RH.Repositories;

import com.PFE.RH.Entities.Conge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongeRepository extends JpaRepository<Conge, Long> {
    List<Conge> findByContactContactId(Long contactId);
}
