package com.PFE.RH.Entities;

import lombok.Data;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

    private String name;
    private String username;
    private String email;
    private String location;
    private String phone;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Absence> absences = new ArrayList<>();

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Prime> primes;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    private List<Autorisation> autorisations;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Conge> conges;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cotisation> cotisations;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "entreprise_id") // Assuming this is the column name in your Contact table
    private Entreprise entreprise;

   // @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    //private List<Impot> impots;

    // Getters and setters
    // Omitted for brevity
}
