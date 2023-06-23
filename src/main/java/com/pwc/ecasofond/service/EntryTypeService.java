package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.EntryType;
import com.pwc.ecasofond.repository.CompanyRepository;
import com.pwc.ecasofond.repository.EntryTypeRepository;
import com.pwc.ecasofond.request.body.add.AddEntryTypeBody;
import com.pwc.ecasofond.request.body.update.UpdateEntryTypeBody;
import com.pwc.ecasofond.request.response.ApiResponse;
import com.pwc.ecasofond.request.response.EntryTypeResponse;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@org.springframework.stereotype.Service
public class EntryTypeService implements Service<EntryTypeResponse, AddEntryTypeBody, UpdateEntryTypeBody, EntryType> {
    private final EntryTypeRepository entryTypeRepository;
    private final CompanyRepository companyRepository;

    public EntryTypeService(EntryTypeRepository entryTypeRepository, CompanyRepository companyRepository) {
        this.entryTypeRepository = entryTypeRepository;
        this.companyRepository = companyRepository;
    }

    public EntryTypeResponse convertToResponse(EntryType entryType) {
        EntryTypeResponse response = new EntryTypeResponse();
        response.setId(entryType.getId());
        response.setName(entryType.getName());
        response.setCompanyId(entryType.getCompanyId());
        return response;
    }

    public ApiResponse<Iterable<EntryTypeResponse>> getAllByCompanyId(Long companyId) {
        ApiResponse<Iterable<EntryTypeResponse>> response = new ApiResponse<>();

        Iterable<EntryType> entryTypes = entryTypeRepository.findAllByCompanyId(companyId);
        ArrayList<EntryTypeResponse> responses = new ArrayList<>();

        for (EntryType entryType : entryTypes) {
            responses.add(convertToResponse(entryType));
        }

        response.setData(responses);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Found " + responses.size() + " entry types");

        return response;
    }

    @Override
    public ApiResponse<Iterable<EntryTypeResponse>> getAll() {
        ApiResponse<Iterable<EntryTypeResponse>> response = new ApiResponse<>();

        Iterable<EntryType> entryTypes = entryTypeRepository.findAll();
        ArrayList<EntryTypeResponse> responses = new ArrayList<>();

        for (EntryType entryType : entryTypes) {
            responses.add(convertToResponse(entryType));
        }

        response.setData(responses);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Found " + responses.size() + " entry types");

        return response;
    }

    @Override
    public ApiResponse<EntryTypeResponse> get(Long id) {
        ApiResponse<EntryTypeResponse> response = new ApiResponse<>();

        EntryType e = entryTypeRepository.findById(id).orElse(null);
        if (e == null) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Entry not found");
            return response;
        }

        response.setData(convertToResponse(e));
        response.setStatus(HttpStatus.OK);
        response.setMessage("Entry type found");

        return response;
    }

    @Override
    public ApiResponse<EntryTypeResponse> add(AddEntryTypeBody entryType) {
        ApiResponse<EntryTypeResponse> response = new ApiResponse<>();

        if (entryTypeRepository.existsByName(entryType.getName())) {
            response.setStatus(HttpStatus.CONFLICT);
            response.setMessage("Entry type already exists");
            return response;
        }

        if (!companyRepository.existsById(entryType.getCompanyId())) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Company not found");
            return response;
        }

        EntryType e = new EntryType();
        e.setName(entryType.getName());
        e.setCompanyId(entryType.getCompanyId());

        response.setData(convertToResponse(entryTypeRepository.save(e)));
        response.setStatus(HttpStatus.OK);
        response.setMessage("Entry type added");

        return response;
    }

    @Override
    public ApiResponse<EntryTypeResponse> update(UpdateEntryTypeBody entryType) {
        ApiResponse<EntryTypeResponse> response = new ApiResponse<>();

        if (entryTypeRepository.existsByName(entryType.getName())) {
            response.setStatus(HttpStatus.CONFLICT);
            response.setMessage("Entry type already exists");
            return response;
        }

        if (!entryTypeRepository.existsById(entryType.getId())) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Entry type not found");
            return response;
        }

        if (!companyRepository.existsById(entryType.getCompanyId())) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Company not found");
            return response;
        }

        EntryType e = entryTypeRepository.findById(entryType.getId()).get();
        e.setName(entryType.getName());
        e.setCompanyId(entryType.getCompanyId());

        response.setData(convertToResponse(entryTypeRepository.save(e)));
        response.setStatus(HttpStatus.OK);
        response.setMessage("Entry type updated");

        return response;
    }

    @Override
    public ApiResponse<Boolean> delete(Long id) {
        ApiResponse<Boolean> response = new ApiResponse<>();

        if (!entryTypeRepository.existsById(id)) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Entry type not found");
            return response;
        }

        EntryType entryType = entryTypeRepository.findById(id).get();
        entryTypeRepository.delete(entryType);

        response.setData(true);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Entry type deleted");

        return response;
    }
}
