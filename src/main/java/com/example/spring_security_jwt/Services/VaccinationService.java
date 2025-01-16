package com.example.spring_security_jwt.Services;

import com.example.spring_security_jwt.Models.Vaccination;
import com.example.spring_security_jwt.Repositories.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccinationService {

    private final VaccinationRepository vaccinationRepository;

    @Autowired
    public VaccinationService(VaccinationRepository vaccinationRepository) {
        this.vaccinationRepository = vaccinationRepository;
    }

    public List<Vaccination> getAllVaccinations() {
        return vaccinationRepository.findAll();
    }

    public Optional<Vaccination> getVaccinationById(Long id) {
        return vaccinationRepository.findById(id);
    }

    public List<Vaccination> getVaccinationsByAnimalId(Long animalId) {
        return vaccinationRepository.findByAnimalId(animalId);
    }

    public Vaccination createVaccination(Vaccination vaccination) {
        return vaccinationRepository.save(vaccination);
    }

    public Vaccination updateVaccination(Long id, Vaccination updatedVaccination) {
        return vaccinationRepository.findById(id).map(vaccination -> {
            vaccination.setName(updatedVaccination.getName());
            vaccination.setDate(updatedVaccination.getDate());
            return vaccinationRepository.save(vaccination);
        }).orElseThrow(() -> new RuntimeException("Vaccination not found"));
    }

    public void deleteVaccination(Long id) {
        vaccinationRepository.deleteById(id);
    }
}