package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.Company;
import com.pwc.ecasofond.repository.CompanyRepository;
import com.pwc.ecasofond.request.body.Body;
import com.pwc.ecasofond.request.body.add.AddCompanyBody;
import com.pwc.ecasofond.request.body.update.UpdateCompanyBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyService implements Service<Company, AddCompanyBody, UpdateCompanyBody> {
    @Autowired
    private CompanyRepository companyRepository;

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
        Company c = new Company();
        c.setName(company.getName());
        return companyRepository.save(c);
    }

    @Override
    public Company update(UpdateCompanyBody company) {
        if (companyRepository.findById(company.getId()).isPresent()) {
            Company c = companyRepository.findById(company.getId()).get();
            c.setName(company.getName());
            return companyRepository.save(c);
        }

        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if (companyRepository.findById(id).isPresent()) {
            Company company = companyRepository.findById(id).get();
            companyRepository.delete(company);
            return true;
        }

        return false;
    }
}
