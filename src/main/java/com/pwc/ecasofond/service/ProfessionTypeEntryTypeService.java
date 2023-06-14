package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.ProfessionTypeEntryType;
import com.pwc.ecasofond.repository.ProfessionTypeEntryTypeRepository;
import com.pwc.ecasofond.request.body.add.AddProfessionTypeEntryTypeBody;
import com.pwc.ecasofond.request.body.update.UpdateProfessionTypeEntryTypeBody;

@org.springframework.stereotype.Service
public class ProfessionTypeEntryTypeService implements Service<ProfessionTypeEntryType, AddProfessionTypeEntryTypeBody, UpdateProfessionTypeEntryTypeBody> {
    private final ProfessionTypeEntryTypeRepository professionTypeEntryTypeRepository;

    public ProfessionTypeEntryTypeService(ProfessionTypeEntryTypeRepository professionTypeEntryTypeRepository) {
        this.professionTypeEntryTypeRepository = professionTypeEntryTypeRepository;
    }

    @Override
    public Iterable<ProfessionTypeEntryType> getAll() {
        return professionTypeEntryTypeRepository.findAll();
    }

    @Override
    public ProfessionTypeEntryType get(Long id) {
        return professionTypeEntryTypeRepository.findById(id).orElse(null);
    }

    @Override
    public ProfessionTypeEntryType add(AddProfessionTypeEntryTypeBody addProfessionTypeEntryTypeBody) {
        if (professionTypeEntryTypeRepository.existsByProfessionIdAndEntryTypeId(addProfessionTypeEntryTypeBody.getProfessionId(), addProfessionTypeEntryTypeBody.getEntryTypeId()))
            return null;

        ProfessionTypeEntryType professionTypeEntryType = new ProfessionTypeEntryType();
        professionTypeEntryType.setProfessionId(addProfessionTypeEntryTypeBody.getProfessionId());
        professionTypeEntryType.setEntryTypeId(addProfessionTypeEntryTypeBody.getEntryTypeId());
        return professionTypeEntryTypeRepository.save(professionTypeEntryType);
    }

    @Override
    public ProfessionTypeEntryType update(UpdateProfessionTypeEntryTypeBody updateProfessionTypeEntryTypeBody) {
        if (professionTypeEntryTypeRepository.existsByProfessionIdAndEntryTypeId(updateProfessionTypeEntryTypeBody.getProfessionId(), updateProfessionTypeEntryTypeBody.getEntryTypeId()))
            return null;

        ProfessionTypeEntryType professionTypeEntryType = new ProfessionTypeEntryType();
        professionTypeEntryType.setProfessionId(updateProfessionTypeEntryTypeBody.getProfessionId());
        professionTypeEntryType.setEntryTypeId(updateProfessionTypeEntryTypeBody.getEntryTypeId());
        return professionTypeEntryTypeRepository.save(professionTypeEntryType);
    }

    @Override
    public Boolean delete(Long id) {
        if (!professionTypeEntryTypeRepository.existsById(id))
            return false;

        professionTypeEntryTypeRepository.deleteById(id);
        return true;
    }
}
