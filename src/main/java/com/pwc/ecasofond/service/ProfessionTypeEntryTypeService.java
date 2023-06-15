package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.ProfessionTypeEntryType;
import com.pwc.ecasofond.repository.ProfessionTypeEntryTypeRepository;
import com.pwc.ecasofond.request.body.add.AddProfessionTypeEntryTypeBody;
import com.pwc.ecasofond.request.body.update.UpdateProfessionTypeEntryTypeBody;
import com.pwc.ecasofond.request.response.ApiResponse;
import com.pwc.ecasofond.request.response.ProfessionTypeEntryTypeResponse;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ProfessionTypeEntryTypeService implements Service<ProfessionTypeEntryTypeResponse, AddProfessionTypeEntryTypeBody, UpdateProfessionTypeEntryTypeBody, ProfessionTypeEntryType> {
    private final ProfessionTypeEntryTypeRepository professionTypeEntryTypeRepository;

    public ProfessionTypeEntryTypeService(ProfessionTypeEntryTypeRepository professionTypeEntryTypeRepository) {
        this.professionTypeEntryTypeRepository = professionTypeEntryTypeRepository;
    }

    @Override
    public ProfessionTypeEntryTypeResponse convertToResponse(ProfessionTypeEntryType professionTypeEntryType) {
        ProfessionTypeEntryTypeResponse professionTypeEntryTypeResponse = new ProfessionTypeEntryTypeResponse();
        professionTypeEntryTypeResponse.setId(professionTypeEntryType.getId());
        professionTypeEntryTypeResponse.setProfessionTypeId(professionTypeEntryType.getProfessionTypeId());
        professionTypeEntryTypeResponse.setEntryTypeId(professionTypeEntryType.getEntryTypeId());
        return professionTypeEntryTypeResponse;
    }

    @Override
    public ApiResponse<Iterable<ProfessionTypeEntryTypeResponse>> getAll() {
        ApiResponse<Iterable<ProfessionTypeEntryTypeResponse>> response = new ApiResponse<>();

        Iterable<ProfessionTypeEntryType> p = professionTypeEntryTypeRepository.findAll();
        List<ProfessionTypeEntryTypeResponse> professionTypeEntryTypeResponses = new ArrayList<>();

        for (ProfessionTypeEntryType professionTypeEntryType : p) {
            professionTypeEntryTypeResponses.add(convertToResponse(professionTypeEntryType));
        }

        response.setData(professionTypeEntryTypeResponses);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Found " + professionTypeEntryTypeResponses.size() + " profession type entry types");

        return response;

    }

    @Override
    public ApiResponse<ProfessionTypeEntryTypeResponse> get(Long id) {
        ApiResponse<ProfessionTypeEntryTypeResponse> response = new ApiResponse<>();

        ProfessionTypeEntryType p = professionTypeEntryTypeRepository.findById(id).orElse(null);
        if (p == null) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Profession type entry type not found");
            return response;
        }

        response.setData(convertToResponse(p));
        response.setStatus(HttpStatus.OK);
        response.setMessage("Found profession type entry type");

        return response;
    }

    @Override
    public ApiResponse<ProfessionTypeEntryTypeResponse> add(AddProfessionTypeEntryTypeBody addProfessionTypeEntryTypeBody) {
        ApiResponse<ProfessionTypeEntryTypeResponse> response = new ApiResponse<>();

        if (professionTypeEntryTypeRepository.existsByProfessionTypeIdAndEntryTypeId(addProfessionTypeEntryTypeBody.getProfessionTypeId(), addProfessionTypeEntryTypeBody.getEntryTypeId())) {
            response.setStatus(HttpStatus.CONFLICT);
            response.setMessage("Profession type entry type already exists");
            return response;
        }

        ProfessionTypeEntryType professionTypeEntryType = new ProfessionTypeEntryType();
        professionTypeEntryType.setProfessionTypeId(addProfessionTypeEntryTypeBody.getProfessionTypeId());
        professionTypeEntryType.setEntryTypeId(addProfessionTypeEntryTypeBody.getEntryTypeId());

        response.setData(convertToResponse(professionTypeEntryTypeRepository.save(professionTypeEntryType)));
        response.setStatus(HttpStatus.OK);
        response.setMessage("Profession type entry type added");

        return response;
    }

    @Override
    public ApiResponse<ProfessionTypeEntryTypeResponse> update(UpdateProfessionTypeEntryTypeBody updateProfessionTypeEntryTypeBody) {
        ApiResponse<ProfessionTypeEntryTypeResponse> response = new ApiResponse<>();

        ProfessionTypeEntryType professionTypeEntryType = professionTypeEntryTypeRepository.findById(updateProfessionTypeEntryTypeBody.getId()).orElse(null);

        if (professionTypeEntryType == null) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Profession type entry type not found");
            return response;
        }

        if (professionTypeEntryTypeRepository.existsByProfessionTypeIdAndEntryTypeId(updateProfessionTypeEntryTypeBody.getProfessionTypeId(), updateProfessionTypeEntryTypeBody.getEntryTypeId())) {
            response.setStatus(HttpStatus.CONFLICT);
            response.setMessage("Profession type entry type already exists");
            return response;
        }

        professionTypeEntryType.setProfessionTypeId(updateProfessionTypeEntryTypeBody.getProfessionTypeId());
        professionTypeEntryType.setEntryTypeId(updateProfessionTypeEntryTypeBody.getEntryTypeId());

        response.setData(convertToResponse(professionTypeEntryTypeRepository.save(professionTypeEntryType)));
        response.setStatus(HttpStatus.OK);
        response.setMessage("Profession type entry type updated");

        return response;
    }

    @Override
    public ApiResponse<Boolean> delete(Long id) {
        ApiResponse<Boolean> response = new ApiResponse<>();

        if (!professionTypeEntryTypeRepository.existsById(id)) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Profession type entry type not found");
            return response;
        }

        professionTypeEntryTypeRepository.deleteById(id);

        response.setData(true);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Profession type entry type deleted");

        return response;
    }
}
