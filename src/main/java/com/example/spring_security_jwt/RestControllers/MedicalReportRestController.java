package com.example.spring_security_jwt.RestControllers;

import com.example.spring_security_jwt.Models.MedicalReport;
import com.example.spring_security_jwt.Repositories.MedicalReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/med-rep")
public class MedicalReportRestController {

    @Autowired
    private MedicalReportRepository medicalReportRepository;

    // Create a medical report
    @PostMapping
    public ResponseEntity<MedicalReport> createMedicalReport(@RequestBody MedicalReport medicalReport) {
        MedicalReport savedReport = medicalReportRepository.save(medicalReport);
        return ResponseEntity.ok(savedReport);
    }

    // Get all medical reports
    @GetMapping
    public List<MedicalReport> getAllMedicalReports() {
        return medicalReportRepository.findAll();
    }

    // Get a medical report by ID
    @GetMapping("/{id}")
    public ResponseEntity<MedicalReport> getMedicalReportById(@PathVariable Long id) {
        Optional<MedicalReport> medicalReport = medicalReportRepository.findById(id);
        return medicalReport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a medical report
    @PutMapping("/{id}")
    public ResponseEntity<MedicalReport> updateMedicalReport(@PathVariable Long id, @RequestBody MedicalReport reportDetails) {
        Optional<MedicalReport> optionalReport = medicalReportRepository.findById(id);

        if (optionalReport.isPresent()) {
            MedicalReport report = optionalReport.get();
            report.setObservation(reportDetails.getObservation());
            report.setMedicalHistory(reportDetails.getMedicalHistory());
            report.setUser(reportDetails.getUser());
            report.setAnimal(reportDetails.getAnimal());
            MedicalReport updatedReport = medicalReportRepository.save(report);
            return ResponseEntity.ok(updatedReport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a medical report
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalReport(@PathVariable Long id) {
        Optional<MedicalReport> medicalReport = medicalReportRepository.findById(id);
        if (medicalReport.isPresent()) {
            medicalReportRepository.delete(medicalReport.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

