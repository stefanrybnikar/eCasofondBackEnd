package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.ProfessionType;
import com.pwc.ecasofond.repository.ProfessionTypeRepository;
import com.pwc.ecasofond.request.body.add.AddProfessionTypeBody;
import com.pwc.ecasofond.request.body.update.UpdateProfessionTypeBody;
import com.pwc.ecasofond.request.response.ApiResponse;
import com.pwc.ecasofond.request.response.ProfessionTypeResponse;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@org.springframework.stereotype.Service
public class ProfessionTypeService implements Service<ProfessionTypeResponse, AddProfessionTypeBody, UpdateProfessionTypeBody, ProfessionType> {
    private final ProfessionTypeRepository professionTypeRepository;

    public ProfessionTypeService(ProfessionTypeRepository professionTypeRepository) {
        this.professionTypeRepository = professionTypeRepository;
    }

    public ProfessionTypeResponse convertToResponse(ProfessionType professionType) {
        ProfessionTypeResponse professionTypeResponse = new ProfessionTypeResponse();
        professionTypeResponse.setId(professionType.getId());
        professionTypeResponse.setName(professionType.getName());
        return professionTypeResponse;
    }

    @Override
    public ApiResponse<Iterable<ProfessionTypeResponse>> getAll() {
        ApiResponse<Iterable<ProfessionTypeResponse>> response = new ApiResponse<>();

        Iterable<ProfessionType> professionTypes = professionTypeRepository.findAll();
        ArrayList<ProfessionTypeResponse> professionTypeResponses = new ArrayList<>();

        for (ProfessionType professionType : professionTypes) {
            professionTypeResponses.add(convertToResponse(professionType));
        }

        response.setData(professionTypeResponses);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Found " + professionTypeResponses.size() + " profession types");

        return response;
    }

    @Override
    public ApiResponse<ProfessionTypeResponse> get(Long id) {
        ApiResponse<ProfessionTypeResponse> response = new ApiResponse<>();

        ProfessionType professionType = professionTypeRepository.findById(id).orElse(null);
        if (professionType == null) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Profession type not found");
            return response;
        }

        response.setData(convertToResponse(professionType));
        response.setStatus(HttpStatus.OK);
        response.setMessage("Profession type found");

        return response;
    }

    @Override
    public ApiResponse<ProfessionTypeResponse> add(AddProfessionTypeBody profession) {
        ApiResponse<ProfessionTypeResponse> response = new ApiResponse<>();

        if (professionTypeRepository.existsByName(profession.getName())) {
            response.setStatus(HttpStatus.CONFLICT);
            response.setMessage("Profession type already exists");
            return response;
        }

        ProfessionType p = new ProfessionType();
        p.setName(profession.getName());

        response.setData(convertToResponse(professionTypeRepository.save(p)));
        response.setStatus(HttpStatus.OK);
        response.setMessage("Profession type added");

        return response;
    }

    @Override
    public ApiResponse<ProfessionTypeResponse> update(UpdateProfessionTypeBody profession) {
        ApiResponse<ProfessionTypeResponse> response = new ApiResponse<>();

        if (!professionTypeRepository.existsById(profession.getId())) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Profession type not found");
            return response;
        }

        if (professionTypeRepository.existsByName(profession.getName())) {
            response.setStatus(HttpStatus.CONFLICT);
            response.setMessage("Profession type already exists");
            return response;
        }

        ProfessionType p = professionTypeRepository.findById(profession.getId()).get();
        p.setName(profession.getName());

        response.setData(convertToResponse(professionTypeRepository.save(p)));
        response.setStatus(HttpStatus.OK);
        response.setMessage("Profession type updated");

        return response;
    }

    @Override
    public ApiResponse<Boolean> delete(Long id) {
        ApiResponse<Boolean> response = new ApiResponse<>();

        if (!professionTypeRepository.existsById(id)) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Profession type not found");
            return response;
        }

        ProfessionType professionType = professionTypeRepository.findById(id).get();
        professionTypeRepository.delete(professionType);

        response.setData(true);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Profession type deleted");

        return response;
    }
}
