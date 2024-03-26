package com.PFE.RH.Controllers;

import com.PFE.RH.Services.SalaryGenerationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;

@RestController
public class SalaryGenerationController {

    private final SalaryGenerationService salaryGenerationService;

    public SalaryGenerationController(SalaryGenerationService salaryGenerationService) {
        this.salaryGenerationService = salaryGenerationService;
    }

    @PostMapping("/generate-salary")
    public void generateSalaryForContact(@RequestBody Map<String, Object> payload) {
        Long contactId = Long.parseLong(payload.get("contactId").toString());
        int year = Integer.parseInt(payload.get("year").toString());
        int month = Integer.parseInt(payload.get("month").toString());

        salaryGenerationService.generateSalaryForContactById(contactId, year, month);
    }
    private double round(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return Double.parseDouble(df.format(value));
    }
}
