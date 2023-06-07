package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.EntryType;
import com.pwc.ecasofond.repository.EntryTypeRepository;
import com.pwc.ecasofond.request.body.add.AddEntryTypeBody;
import com.pwc.ecasofond.request.body.update.UpdateEntryTypeBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntryTypeService implements Service<EntryType, AddEntryTypeBody, UpdateEntryTypeBody> {
    @Autowired
    private EntryTypeRepository entryTypeRepository;

    @Override
    public Iterable<EntryType> getAll() {
        return entryTypeRepository.findAll();
    }

    @Override
    public EntryType get(Long id) {
        return entryTypeRepository.findById(id).orElse(null);
    }

    @Override
    public EntryType add(AddEntryTypeBody entryType) {
        EntryType e = new EntryType();
        e.setName(entryType.getName());
        return entryTypeRepository.save(e);
    }

    @Override
    public EntryType update(UpdateEntryTypeBody entryType) {
        if (entryTypeRepository.findById(entryType.getId()).isPresent()) {
            EntryType e = entryTypeRepository.findById(entryType.getId()).get();
            e.setName(entryType.getName());
            return entryTypeRepository.save(e);
        }

        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if (entryTypeRepository.findById(id).isPresent()) {
            EntryType entryType = entryTypeRepository.findById(id).get();
            entryTypeRepository.delete(entryType);
            return true;
        }

        return false;
    }
}
