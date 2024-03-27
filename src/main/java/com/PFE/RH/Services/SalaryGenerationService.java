package com.PFE.RH.Services;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
        double x1 = salaireImposable * 0.9;
        double x2 = x1 * 12;
        double x3 = x2; // Without Deduction

        double resulterpp = 0;
        double resultcss = 0;

        if (x3 >= 0 && x3 <= 5000) {
            resulterpp = 0;
            resultcss = x3 * 0;
        } else if (x3 >= 5000 && x3 <= 20000) {
            double css0 = 5000 * 0;
            resulterpp = (x3 - 5000) * 0.26;
            resultcss = (x3 - 5000) * 0.265 + css0;
        } else if (x3 >= 20000 && x3 <= 30000) {
            double css0 = 5000 * 0;
            double errp1 = 15000 * 0.26;
            double css1 = 15000 * 0.265;
            resulterpp = (x3 - 20000) * 0.28 + errp1;
            resultcss = (x3 - 20000) * 0.285 + css0 + css1;
        } else if (x3 >= 30000 && x3 <= 50000) {
            double css0 = 5000 * 0;
            double errp1 = 15000 * 0.26;
            double css1 = 15000 * 0.265;
            double errp2 = 10000 * 0.28;
            double css2 = 10000 * 0.285;
            resulterpp = (x3 - 30000) * 0.32 + errp2 + errp1;
            resultcss = (x3 - 30000) * 0.325 + css0 + css1 + css2;
        } else if (x3 > 50000) {
            double css0 = 5000 * 0;
            double errp1 = 15000 * 0.26;
            double css1 = 15000 * 0.265;
            double errp2 = 10000 * 0.28;
            double css2 = 10000 * 0.285;
            double errp3 = 20000 * 0.32;
            double css3 = 20000 * 0.325;
            resulterpp = (x3 - 50000) * 0.35 + errp3 + errp2 + errp1;
            resultcss = (x3 - 50000) * 0.355 + css0 + css1 + css2 + css3;
        }

        double erpp = resulterpp / 12;
        //double erpp = 1164.350;
        double css = (resultcss - resulterpp) / 12;
        double salaireNet = salaireImposable - erpp - css;

        // Round the values to two decimal places
        ficheDePaie.setSalaireNet(round(salaireNet));
        ficheDePaie.setCss(round(css));
        ficheDePaie.setErpp(round(erpp));
        ficheDePaie.setPrime(sommePrimes); // Prime set
        // Persist the FicheDePaie object
        ficheDePaieService.persist(ficheDePaie);
        byte[] pdfContent = generatePayslipPDF(contact, year, month, salaireBrute, sommePrimes, salaireImposable, salaireNet, css, erpp);
        savePayslipPDFToFile(pdfContent, "/home/amen/Desktop/fiche_de_paie.pdf");
    }

    private double round(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return Double.parseDouble(df.format(value));
    }
    private byte[] generatePayslipPDF(Contact contact, int year, int month, double salaireBrute,
                                      double sommePrimes, double salaireImposable, double salaireNet,
                                      double css, double erpp) {
        try {
            Document document = new Document();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, byteArrayOutputStream);

            document.open();

            // Add content to the PDF
            document.add(new Paragraph("Fiche de Paie"));
            document.add(new Paragraph("Nom: " + contact.getName()));
            document.add(new Paragraph("Année: " + year));
            document.add(new Paragraph("Mois: " + month));
            document.add(new Paragraph("Salaire Brut: " + round(salaireBrute)));
            document.add(new Paragraph("Somme des Primes: " + round(sommePrimes)));
            document.add(new Paragraph("Salaire Imposable: " + round(salaireImposable)));
            document.add(new Paragraph("CSS: " + round(css)));
            document.add(new Paragraph("ERPP: " + round(erpp)));
            document.add(new Paragraph("Salaire Net: " + round(salaireNet)));


            document.close();

            return byteArrayOutputStream.toByteArray();
        } catch (DocumentException  e) {
            e.printStackTrace();
            // Handle exception
            return null;
        }
    }

    private void savePayslipPDFToFile(byte[] pdfContent, String filePath) {
        if (pdfContent != null) {
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(pdfContent);
                System.out.println("Fiche de Paie PDF saved to: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle exception
            }
        } else {
            System.out.println("Failed to generate Fiche de Paie PDF.");
        }
    }
}
