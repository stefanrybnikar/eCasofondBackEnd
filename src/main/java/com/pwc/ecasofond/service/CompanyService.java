package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.Company;
import com.pwc.ecasofond.repository.CompanyRepository;
import com.pwc.ecasofond.request.body.add.AddCompanyBody;
import com.pwc.ecasofond.request.body.update.UpdateCompanyBody;
import com.pwc.ecasofond.request.response.CompanyResponse;

import java.util.ArrayList;

@org.springframework.stereotype.Service
public class CompanyService implements Service<CompanyResponse, AddCompanyBody, UpdateCompanyBody, Company> {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyResponse convertToResponse(Company company) {
        CompanyResponse response = new CompanyResponse();
        response.setId(company.getId());
        response.setName(company.getName());
        return response;
    }

    @Override
    public Iterable<CompanyResponse> getAll() {
        Iterable<Company> companies = companyRepository.findAll();
        ArrayList<CompanyResponse> responses = new ArrayList<>();
        for (Company company : companies) {
            responses.add(convertToResponse(company));
        }
        return responses;
    }

    @Override
    public CompanyResponse get(Long id) {
        Company c = companyRepository.findById(id).orElse(null);
        if (c == null)
            return null;

        return convertToResponse(c);
    }

    @Override
    public CompanyResponse add(AddCompanyBody company) {
        if (companyRepository.existsByName(company.getName()))
            return null;

        Company c = new Company();
        c.setName(company.getName());
        return convertToResponse(companyRepository.save(c));
    }

    @Override
    public CompanyResponse update(UpdateCompanyBody company) {
        if (companyRepository.existsByName(company.getName()))
            return null;

        if (!companyRepository.existsById(company.getId()))
            return null;

        Company c = companyRepository.findById(company.getId()).get();
        c.setName(company.getName());
        return convertToResponse(companyRepository.save(c));
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
