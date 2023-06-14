package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.ProfessionType;
import com.pwc.ecasofond.repository.ProfessionTypeRepository;
import com.pwc.ecasofond.request.body.add.AddProfessionTypeBody;
import com.pwc.ecasofond.request.body.update.UpdateProfessionTypeBody;

@org.springframework.stereotype.Service
public class ProfessionTypeService implements Service<ProfessionType, AddProfessionTypeBody, UpdateProfessionTypeBody> {
    private final ProfessionTypeRepository professionTypeRepository;

    public ProfessionTypeService(ProfessionTypeRepository professionTypeRepository) {
        this.professionTypeRepository = professionTypeRepository;
    }

    @Override
    public Iterable<ProfessionType> getAll() {
        return professionTypeRepository.findAll();
    }

    @Override
    public ProfessionType get(Long id) {
        return professionTypeRepository.findById(id).orElse(null);
    }

    @Override
    public ProfessionType add(AddProfessionTypeBody profession) {
        if (professionTypeRepository.existsByName(profession.getName()))
            return null;

        ProfessionType p = new ProfessionType();
        p.setName(profession.getName());
        return professionTypeRepository.save(p);
    }

    @Override
    public ProfessionType update(UpdateProfessionTypeBody profession) {
        if (!professionTypeRepository.existsById(profession.getId()))
            return null;

        if (!professionTypeRepository.existsByName(profession.getName()))
            return null;

        ProfessionType p = professionTypeRepository.findById(profession.getId()).get();
        p.setName(profession.getName());
        return professionTypeRepository.save(p);
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
