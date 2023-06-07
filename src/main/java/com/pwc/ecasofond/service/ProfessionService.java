package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.Profession;
import com.pwc.ecasofond.repository.ProfessionRepository;
import com.pwc.ecasofond.request.body.add.AddProfessionBody;
import com.pwc.ecasofond.request.body.update.UpdateProfessionBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfessionService implements Service<Profession, AddProfessionBody, UpdateProfessionBody> {
    @Autowired
    private ProfessionRepository professionRepository;

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
        Profession p = new Profession();
        p.setName(profession.getName());
        return professionRepository.save(p);
    }

    @Override
    public Profession update(UpdateProfessionBody profession) {
        if (professionRepository.findById(profession.getId()).isPresent()) {
            Profession p = professionRepository.findById(profession.getId()).get();
            p.setName(profession.getName());
            return professionRepository.save(p);
        }

        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if (professionRepository.findById(id).isPresent()) {
            Profession profession = professionRepository.findById(id).get();
            professionRepository.delete(profession);
            return true;
        }

        return false;
    }
}
