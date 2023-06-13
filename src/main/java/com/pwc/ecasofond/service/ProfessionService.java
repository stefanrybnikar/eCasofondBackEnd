package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.Profession;
import com.pwc.ecasofond.repository.ProfessionRepository;
import com.pwc.ecasofond.request.body.add.AddProfessionBody;
import com.pwc.ecasofond.request.body.update.UpdateProfessionBody;

@org.springframework.stereotype.Service
public class ProfessionService implements Service<Profession, AddProfessionBody, UpdateProfessionBody> {
    private final ProfessionRepository professionRepository;

    public ProfessionService(ProfessionRepository professionRepository) {
        this.professionRepository = professionRepository;
    }

    @Override
    public Iterable<Profession> getAll() {
        return professionRepository.findAll();
    }

    @Override
    public Profession get(Long id) {
        return professionRepository.findById(id).orElse(null);
    }

    @Override
    public Profession add(AddProfessionBody profession) {
        if (professionRepository.existsByName(profession.getName()))
            return null;

        Profession p = new Profession();
        p.setName(profession.getName());
        return professionRepository.save(p);
    }

    @Override
    public Profession update(UpdateProfessionBody profession) {
        if (!professionRepository.existsById(profession.getId()))
            return null;

        if (!professionRepository.existsByName(profession.getName()))
            return null;

        Profession p = professionRepository.findById(profession.getId()).get();
        p.setName(profession.getName());
        return professionRepository.save(p);
    }

    @Override
    public Boolean delete(Long id) {
        if (!professionRepository.existsById(id))
            return false;

        Profession profession = professionRepository.findById(id).get();
        professionRepository.delete(profession);
        return true;
    }
}
