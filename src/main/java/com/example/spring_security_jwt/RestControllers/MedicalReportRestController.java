package com.example.spring_security_jwt.RestControllers;

import com.example.spring_security_jwt.Models.MedicalReport;
import com.example.spring_security_jwt.Services.MedicalReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/med-rep/")
public class MedicalReportRestController {

    @Autowired
    private MedicalReportService medicalReportService;

    @PostMapping
    public ResponseEntity<MedicalReport> createMedicalReport(@RequestBody MedicalReport medicalReport) {
        MedicalReport savedReport = medicalReportService.createMedicalReport(medicalReport);
        return ResponseEntity.ok(savedReport);
    }

    @GetMapping
    public List<MedicalReport> getAllMedicalReports() {
        return medicalReportService.getAllMedicalReports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalReport> getMedicalReportById(@PathVariable Long id) {
        Optional<MedicalReport> medicalReport = medicalReportService.getMedicalReportById(id);
        return medicalReport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalReport> updateMedicalReport(@PathVariable Long id, @RequestBody MedicalReport reportDetails) {
        MedicalReport updatedReport = medicalReportService.updateMedicalReport(id, reportDetails);
        if (updatedReport != null) {
            return ResponseEntity.ok(updatedReport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicalReport(@PathVariable Long id) {
        try {
            medicalReportService.deleteMedicalReport(id);
            return ResponseEntity.ok("Medical report with ID " + id + " has been successfully deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete medical report with ID " + id + ": " + e.getMessage());
        }
    }

}
