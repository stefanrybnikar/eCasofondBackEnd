package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.Company;
import com.pwc.ecasofond.repository.CompanyRepository;
import com.pwc.ecasofond.request.body.add.AddCompanyBody;
import com.pwc.ecasofond.request.body.update.UpdateCompanyBody;

@org.springframework.stereotype.Service
public class CompanyService implements Service<Company, AddCompanyBody, UpdateCompanyBody> {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Iterable<Company> getAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company get(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company add(AddCompanyBody company) {
        if (!companyRepository.existsByName(company.getName()))
            return null;

        Company c = new Company();
        c.setName(company.getName());
        return companyRepository.save(c);
    }

    @Override
    public Company update(UpdateCompanyBody company) {
        if (companyRepository.existsByName(company.getName()))
            return null;

        if (!companyRepository.existsById(company.getId()))
            return null;

        Company c = companyRepository.findById(company.getId()).get();
        c.setName(company.getName());
        return companyRepository.save(c);
    }

    @Override
    public Boolean delete(Long id) {
        if (!companyRepository.existsById(id))
            return false;

        Company company = companyRepository.findById(id).get();
        companyRepository.delete(company);
        return true;
    }
}
