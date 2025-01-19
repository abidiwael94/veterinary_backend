package com.example.spring_security_jwt.RestControllers;

import com.example.spring_security_jwt.Models.Vaccination;
import com.example.spring_security_jwt.Repositories.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vac")
public class VaccinationRestController {

    @Autowired
    private VaccinationRepository vaccinationRepository;

    // Get all vaccinations
    @GetMapping
    public List<Vaccination> getAllVaccinations() {
        return vaccinationRepository.findAll();
    }

    // Get a specific vaccination by ID
    @GetMapping("/{id}")
    public Optional<Vaccination> getVaccinationById(@PathVariable Long id) {
        return vaccinationRepository.findById(id);
    }

    // Create a new vaccination
    @PostMapping
    public Vaccination createVaccination(@RequestBody Vaccination vaccination) {
        return vaccinationRepository.save(vaccination);
    }

    // Update a vaccination
    @PutMapping("/{id}")
    public Vaccination updateVaccination(@PathVariable Long id, @RequestBody Vaccination vaccinationDetails) {
        return vaccinationRepository.findById(id).map(vaccination -> {
            vaccination.setName(vaccinationDetails.getName());
            vaccination.setDate(vaccinationDetails.getDate());
            vaccination.setAnimal(vaccinationDetails.getAnimal());
            return vaccinationRepository.save(vaccination);
        }).orElseThrow(() -> new RuntimeException("Vaccination not found with id " + id));
    }

    // Delete a vaccination
    @DeleteMapping("/{id}")
    public void deleteVaccination(@PathVariable Long id) {
        vaccinationRepository.deleteById(id);
    }
}
