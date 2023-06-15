package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.Company;
import com.pwc.ecasofond.repository.CompanyRepository;
import com.pwc.ecasofond.request.body.add.AddCompanyBody;
import com.pwc.ecasofond.request.body.update.UpdateCompanyBody;
import com.pwc.ecasofond.request.response.ApiResponse;
import com.pwc.ecasofond.request.response.CompanyResponse;
import org.springframework.http.HttpStatus;

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
    public ApiResponse<Iterable<CompanyResponse>> getAll() {
        Iterable<Company> companies = companyRepository.findAll();
        ArrayList<CompanyResponse> responses = new ArrayList<>();
        for (Company company : companies) {
            responses.add(convertToResponse(company));
        }

        ApiResponse<Iterable<CompanyResponse>> response = new ApiResponse<>();
        response.setData(responses);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Found " + responses.size() + " companies");

        return response;
    }

    @Override
    public ApiResponse<CompanyResponse> get(Long id) {
        ApiResponse<CompanyResponse> response = new ApiResponse<>();

        Company c = companyRepository.findById(id).orElse(null);
        if (c == null) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Company not found");
            return response;
        }

        response.setData(convertToResponse(c));
        response.setStatus(HttpStatus.OK);
        response.setMessage("Company found");

        return response;
    }

    @Override
    public ApiResponse<CompanyResponse> add(AddCompanyBody company) {
        ApiResponse<CompanyResponse> response = new ApiResponse<>();

        if (companyRepository.existsByName(company.getName())) {
            response.setStatus(HttpStatus.CONFLICT);
            response.setMessage("Company with this name already exists");
            return response;
        }

        Company c = new Company();
        c.setName(company.getName());

        response.setStatus(HttpStatus.OK);
        response.setMessage("Company added");
        response.setData(convertToResponse(companyRepository.save(c)));

        return response;
    }

    @Override
    public ApiResponse<CompanyResponse> update(UpdateCompanyBody company) {
        ApiResponse<CompanyResponse> response = new ApiResponse<>();

        if (companyRepository.existsByName(company.getName())) {
            response.setStatus(HttpStatus.CONFLICT);
            response.setMessage("Company with this name already exists");
            return response;
        }

        if (!companyRepository.existsById(company.getId())) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Company not found");
            return response;
        }

        Company c = companyRepository.findById(company.getId()).get();
        c.setName(company.getName());

        response.setStatus(HttpStatus.OK);
        response.setMessage("Company updated");
        response.setData(convertToResponse(companyRepository.save(c)));

        return response;
    }

    @Override
    public ApiResponse<Boolean> delete(Long id) {
        ApiResponse<Boolean> response = new ApiResponse<>();

        if (!companyRepository.existsById(id)) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Company not found");
            return response;
        }

        Company company = companyRepository.findById(id).get();
        companyRepository.delete(company);

        response.setStatus(HttpStatus.OK);
        response.setMessage("Company deleted");
        response.setData(true);

        return response;
    }
}
