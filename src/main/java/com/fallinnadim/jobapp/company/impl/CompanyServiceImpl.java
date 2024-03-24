package com.fallinnadim.jobapp.company.impl;

import com.fallinnadim.jobapp.company.Company;
import com.fallinnadim.jobapp.company.CompanyRepository;
import com.fallinnadim.jobapp.company.CompanyService;
import com.fallinnadim.jobapp.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company updatedCompany, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setJobs(updatedCompany.getJobs());
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }
}
