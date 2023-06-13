package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.EntryType;
import com.pwc.ecasofond.repository.EntryTypeRepository;
import com.pwc.ecasofond.request.body.add.AddEntryTypeBody;
import com.pwc.ecasofond.request.body.update.UpdateEntryTypeBody;

@org.springframework.stereotype.Service
public class EntryTypeService implements Service<EntryType, AddEntryTypeBody, UpdateEntryTypeBody> {
    private final EntryTypeRepository entryTypeRepository;

    public EntryTypeService(EntryTypeRepository entryTypeRepository) {
        this.entryTypeRepository = entryTypeRepository;
    }

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
        if (entryTypeRepository.existsByName(entryType.getName()))
            return null;

        EntryType e = new EntryType();
        e.setName(entryType.getName());
        return entryTypeRepository.save(e);
    }

    @Override
    public EntryType update(UpdateEntryTypeBody entryType) {
        if (entryTypeRepository.existsByName(entryType.getName()))
            return null;

        if (!entryTypeRepository.existsById(entryType.getId()))
            return null;

        EntryType e = entryTypeRepository.findById(entryType.getId()).get();
        e.setName(entryType.getName());
        return entryTypeRepository.save(e);
    }

    @Override
    public Boolean delete(Long id) {
        if (!entryTypeRepository.existsById(id))
            return false;

        EntryType entryType = entryTypeRepository.findById(id).get();
        entryTypeRepository.delete(entryType);
        return true;
    }
}
