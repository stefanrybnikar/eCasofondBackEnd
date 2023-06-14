package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.EntryType;
import com.pwc.ecasofond.repository.EntryTypeRepository;
import com.pwc.ecasofond.request.body.add.AddEntryTypeBody;
import com.pwc.ecasofond.request.body.update.UpdateEntryTypeBody;
import com.pwc.ecasofond.request.response.EntryTypeResponse;

import java.util.ArrayList;

@org.springframework.stereotype.Service
public class EntryTypeService implements Service<EntryTypeResponse, AddEntryTypeBody, UpdateEntryTypeBody, EntryType> {
    private final EntryTypeRepository entryTypeRepository;

    public EntryTypeService(EntryTypeRepository entryTypeRepository) {
        this.entryTypeRepository = entryTypeRepository;
    }

    public EntryTypeResponse convertToResponse(EntryType entryType) {
        EntryTypeResponse response = new EntryTypeResponse();
        response.setId(entryType.getId());
        response.setName(entryType.getName());
        return response;
    }

    @Override
    public Iterable<EntryTypeResponse> getAll() {
        Iterable<EntryType> entryTypes = entryTypeRepository.findAll();
        ArrayList<EntryTypeResponse> responses = new ArrayList<>();

        for (EntryType entryType : entryTypes) {
            responses.add(convertToResponse(entryType));
        }

        return responses;
    }

    @Override
    public EntryTypeResponse get(Long id) {
        EntryType e = entryTypeRepository.findById(id).orElse(null);
        if (e == null)
            return null;

        return convertToResponse(e);
    }

    @Override
    public EntryTypeResponse add(AddEntryTypeBody entryType) {
        if (entryTypeRepository.existsByName(entryType.getName()))
            return null;

        EntryType e = new EntryType();
        e.setName(entryType.getName());
        return convertToResponse(entryTypeRepository.save(e));
    }

    @Override
    public EntryTypeResponse update(UpdateEntryTypeBody entryType) {
        if (entryTypeRepository.existsByName(entryType.getName()))
            return null;

        if (!entryTypeRepository.existsById(entryType.getId()))
            return null;

        EntryType e = entryTypeRepository.findById(entryType.getId()).get();
        e.setName(entryType.getName());
        return convertToResponse(entryTypeRepository.save(e));
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
