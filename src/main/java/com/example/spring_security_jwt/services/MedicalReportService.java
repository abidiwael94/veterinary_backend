package com.example.spring_security_jwt.services;

import com.example.spring_security_jwt.models.MedicalReport;
import com.example.spring_security_jwt.repositories.MedicalReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalReportService {

    private final MedicalReportRepository medicalReportRepository;

    @Autowired
    public MedicalReportService(MedicalReportRepository medicalReportRepository) {
        this.medicalReportRepository = medicalReportRepository;
    }

    public List<MedicalReport> getAllMedicalReports() {
        return medicalReportRepository.findAll();
    }

    public Optional<MedicalReport> getMedicalReportById(Long id) {
        return medicalReportRepository.findById(id);
    }

    public List<MedicalReport> getMedicalReportsByUserId(Long userId) {
        return medicalReportRepository.findByUserId(userId);
    }

    public List<MedicalReport> getMedicalReportsByAnimalId(Long animalId) {
        return medicalReportRepository.findByAnimalId(animalId);
    }

    public MedicalReport createMedicalReport(MedicalReport medicalReport) {
        return medicalReportRepository.save(medicalReport);
    }

    public MedicalReport updateMedicalReport(Long id, MedicalReport updatedMedicalReport) {
        return medicalReportRepository.findById(id).map(medicalReport -> {
            medicalReport.setObservation(updatedMedicalReport.getObservation());
            medicalReport.setMedicalHistory(updatedMedicalReport.getMedicalHistory());
            return medicalReportRepository.save(medicalReport);
        }).orElseThrow(() -> new RuntimeException("Medical Report not found"));
    }

    public void deleteMedicalReport(Long id) {
        medicalReportRepository.deleteById(id);
    }
}
