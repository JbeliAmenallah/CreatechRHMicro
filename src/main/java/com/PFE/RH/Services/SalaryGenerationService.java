package com.PFE.RH.Services;

import com.PFE.RH.Entities.Contact;
import com.PFE.RH.Entities.FicheDePaie;
import com.PFE.RH.Entities.FinanceConfiguration;
import com.PFE.RH.Entities.Prime;
import com.PFE.RH.Repositories.ContactRepository;
import com.PFE.RH.Repositories.FinanceConfigurationRepository;
import com.PFE.RH.Repositories.PrimeRepository;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

@Service
public class SalaryGenerationService {

    private final ContactRepository contactRepository;
    private final FicheDePaieService ficheDePaieService;
    private final FinanceConfigurationRepository financeConfigurationRepository;
    private final PrimeRepository primeRepository;

    public SalaryGenerationService(ContactRepository contactRepository, FicheDePaieService ficheDePaieService, FinanceConfigurationRepository financeConfigurationRepository, PrimeRepository primeRepository) {
        this.contactRepository = contactRepository;
        this.ficheDePaieService = ficheDePaieService;
        this.financeConfigurationRepository = financeConfigurationRepository;
        this.primeRepository = primeRepository;
    }

    public void generateSalaryForContactById(Long contactId, int year, int month) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found with ID: " + contactId));

        FicheDePaie ficheDePaie = new FicheDePaie();
        ficheDePaie.setContact(contact);
        ficheDePaie.setYear(year);
        ficheDePaie.setMonth(month);
        // Fetch the FinanceConfiguration with libelle "Cnss"
        FinanceConfiguration cnssFinanceConfiguration = financeConfigurationRepository.findByLibeleAndYear("Cnss", year)
                .orElseThrow(() -> new RuntimeException("Finance Configuration 'Cnss' not found for year: " + year));
        // Fetch the list of primes for the given contactId and year
        List<Prime> primes = primeRepository.findByContactContactIdAndYear(contactId, year);

        // Calculate the sum of primes
        double sommePrimes = primes.stream()
                .mapToDouble(Prime::getMontant)
                .sum();
        // Perform salary calculation
        double tauxCNSS = cnssFinanceConfiguration.getTaux(); // Get the Taux from Cnss Finance Configuration
        double salaireBase = contact.getSalaireDeBASE();
        double salaireBrute = salaireBase+ sommePrimes; // With Prime
        double salaireImposable = salaireBrute - (salaireBrute * tauxCNSS);
        double x1 = salaireImposable * 0.90;
        double x2 = x1 * 12;
        double x3 = x2; // Without Deduction

        double resulterpp = 0;
        double resultcss = 0;

        if (x3 >= 0 && x3 <= 5000) {
            resulterpp = 0;
            resultcss = x3 * 0.01;
        } else if (x3 >= 5000 && x3 <= 20000) {
            double css0 = 5000 * 0.01;
            resulterpp = (x3 - 5000) * 0.26;
            resultcss = (x3 - 5000) * 0.27 + css0;
        } else if (x3 >= 20000 && x3 <= 30000) {
            double css0 = 5000 * 0.01;
            double errp1 = 15000 * 0.26;
            double css1 = 15000 * 0.27;
            resulterpp = (x3 - 20000) * 0.28 + errp1;
            resultcss = (x3 - 20000) * 0.29 + css0 + css1;
        } else if (x3 >= 30000 && x3 <= 50000) {
            double css0 = 5000 * 0.01;
            double errp1 = 15000 * 0.26;
            double css1 = 15000 * 0.27;
            double errp2 = 10000 * 0.28;
            double css2 = 10000 * 0.29;
            resulterpp = (x3 - 30000) * 0.32 + errp2;
            resultcss = (x3 - 30000) * 0.33 + css0 + css1 + css2;
        } else if (x3 > 50000) {
            double css0 = 5000 * 0.01;
            double errp1 = 15000 * 0.26;
            double css1 = 15000 * 0.27;
            double errp2 = 10000 * 0.28;
            double css2 = 10000 * 0.29;
            double errp3 = 20000 * 0.32;
            double css3 = 20000 * 0.33;
            resulterpp = (x3 - 50000) * 0.35;
            resultcss = (x3 - 50000) * 0.36 + css0 + css1 + css2 + css3;
        }

        double erpp = resulterpp / 12;
        double css = (resultcss / 12) - erpp;
        double salaireNet = salaireImposable - erpp - css;

        // Round the values to two decimal places
        ficheDePaie.setSalaireNet(round(salaireNet));
        ficheDePaie.setCss(round(css));
        ficheDePaie.setErpp(round(erpp));
        ficheDePaie.setPrime(sommePrimes); // Prime set
        // Persist the FicheDePaie object
        ficheDePaieService.persist(ficheDePaie);
    }

    private double round(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return Double.parseDouble(df.format(value));
    }
}
