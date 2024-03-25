package com.fallinnadim.jobapp.company;

import com.fallinnadim.jobapp.job.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        boolean updated = companyService.updateCompany(company,id);
        if (!updated) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Company company) {
        companyService.createCompany(company);
        return new ResponseEntity<>("Company added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {
        boolean deleted = companyService.deleteCompanyById(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Successfully deleted company",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
}
