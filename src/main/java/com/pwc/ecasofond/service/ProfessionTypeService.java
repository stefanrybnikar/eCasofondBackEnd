package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.ProfessionType;
import com.pwc.ecasofond.repository.ProfessionTypeRepository;
import com.pwc.ecasofond.request.body.add.AddProfessionTypeBody;
import com.pwc.ecasofond.request.body.update.UpdateProfessionTypeBody;
import com.pwc.ecasofond.request.response.ProfessionTypeResponse;

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
    public Iterable<ProfessionTypeResponse> getAll() {
        Iterable<ProfessionType> professionTypes = professionTypeRepository.findAll();
        ArrayList<ProfessionTypeResponse> professionTypeResponses = new ArrayList<>();

        for (ProfessionType professionType : professionTypes) {
            professionTypeResponses.add(convertToResponse(professionType));
        }

        return professionTypeResponses;
    }

    @Override
    public ProfessionTypeResponse get(Long id) {
        ProfessionType professionType = professionTypeRepository.findById(id).orElse(null);
        if (professionType == null)
            return null;

        return convertToResponse(professionType);
    }

    @Override
    public ProfessionTypeResponse add(AddProfessionTypeBody profession) {
        if (professionTypeRepository.existsByName(profession.getName()))
            return null;

        ProfessionType p = new ProfessionType();
        p.setName(profession.getName());

        return convertToResponse(professionTypeRepository.save(p));
    }

    @Override
    public ProfessionTypeResponse update(UpdateProfessionTypeBody profession) {
        if (!professionTypeRepository.existsById(profession.getId()))
            return null;

        if (professionTypeRepository.existsByName(profession.getName()))
            return null;

        ProfessionType p = professionTypeRepository.findById(profession.getId()).get();
        p.setName(profession.getName());

        return convertToResponse(professionTypeRepository.save(p));
    }

    @Override
    public Boolean delete(Long id) {
        if (!professionTypeRepository.existsById(id))
            return false;

        ProfessionType professionType = professionTypeRepository.findById(id).get();
        professionTypeRepository.delete(professionType);
        return true;
    }
}
