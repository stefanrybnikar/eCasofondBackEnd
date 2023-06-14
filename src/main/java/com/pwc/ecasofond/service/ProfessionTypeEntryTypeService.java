package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.ProfessionTypeEntryType;
import com.pwc.ecasofond.repository.ProfessionTypeEntryTypeRepository;
import com.pwc.ecasofond.request.body.add.AddProfessionTypeEntryTypeBody;
import com.pwc.ecasofond.request.body.update.UpdateProfessionTypeEntryTypeBody;
import com.pwc.ecasofond.request.response.ProfessionTypeEntryTypeResponse;

import java.util.ArrayList;

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
    public Iterable<ProfessionTypeEntryTypeResponse> getAll() {
        Iterable<ProfessionTypeEntryType> p = professionTypeEntryTypeRepository.findAll();
        ArrayList<ProfessionTypeEntryTypeResponse> response = new ArrayList<>();

        for (ProfessionTypeEntryType professionTypeEntryType : p) {
            response.add(convertToResponse(professionTypeEntryType));
        }

        return response;
    }

    @Override
    public ProfessionTypeEntryTypeResponse get(Long id) {
        ProfessionTypeEntryType p = professionTypeEntryTypeRepository.findById(id).orElse(null);
        if (p == null)
            return null;

        return convertToResponse(p);
    }

    @Override
    public ProfessionTypeEntryTypeResponse add(AddProfessionTypeEntryTypeBody addProfessionTypeEntryTypeBody) {
        if (professionTypeEntryTypeRepository.existsByProfessionTypeIdAndEntryTypeId(addProfessionTypeEntryTypeBody.getProfessionTypeId(), addProfessionTypeEntryTypeBody.getEntryTypeId()))
            return null;

        ProfessionTypeEntryType professionTypeEntryType = new ProfessionTypeEntryType();
        professionTypeEntryType.setProfessionTypeId(addProfessionTypeEntryTypeBody.getProfessionTypeId());
        professionTypeEntryType.setEntryTypeId(addProfessionTypeEntryTypeBody.getEntryTypeId());
        return convertToResponse(professionTypeEntryTypeRepository.save(professionTypeEntryType));
    }

    @Override
    public ProfessionTypeEntryTypeResponse update(UpdateProfessionTypeEntryTypeBody updateProfessionTypeEntryTypeBody) {
        if (professionTypeEntryTypeRepository.existsByProfessionTypeIdAndEntryTypeId(updateProfessionTypeEntryTypeBody.getProfessionTypeId(), updateProfessionTypeEntryTypeBody.getEntryTypeId()))
            return null;

        ProfessionTypeEntryType professionTypeEntryType = new ProfessionTypeEntryType();
        professionTypeEntryType.setProfessionTypeId(updateProfessionTypeEntryTypeBody.getProfessionTypeId());
        professionTypeEntryType.setEntryTypeId(updateProfessionTypeEntryTypeBody.getEntryTypeId());
        return convertToResponse(professionTypeEntryTypeRepository.save(professionTypeEntryType));
    }

    @Override
    public Boolean delete(Long id) {
        if (!professionTypeEntryTypeRepository.existsById(id))
            return false;

        professionTypeEntryTypeRepository.deleteById(id);
        return true;
    }
}
