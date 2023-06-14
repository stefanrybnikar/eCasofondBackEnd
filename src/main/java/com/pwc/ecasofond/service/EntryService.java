package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.Entry;
import com.pwc.ecasofond.repository.EntryRepository;
import com.pwc.ecasofond.repository.EntryTypeRepository;
import com.pwc.ecasofond.repository.UserRepository;
import com.pwc.ecasofond.request.body.add.AddEntryBody;
import com.pwc.ecasofond.request.body.update.UpdateEntryBody;
import com.pwc.ecasofond.request.response.EntryResponse;

import java.sql.Timestamp;
import java.util.ArrayList;

@org.springframework.stereotype.Service
public class EntryService implements Service<EntryResponse, AddEntryBody, UpdateEntryBody, Entry> {
    private final EntryRepository entryRepository;
    private final UserRepository userRepository;
    private final EntryTypeRepository entryTypeRepository;

    public EntryService(EntryRepository entryRepository, UserRepository userRepository, EntryTypeRepository entryTypeRepository) {
        this.entryRepository = entryRepository;
        this.userRepository = userRepository;
        this.entryTypeRepository = entryTypeRepository;
    }

    public EntryResponse convertToResponse(Entry entry) {
        EntryResponse entryResponse = new EntryResponse();
        entryResponse.setId(entry.getId());
        entryResponse.setUserId(entry.getUserId());
        entryResponse.setTypeId(entry.getTypeId());
        entryResponse.setDescription(entry.getDescription());
        entryResponse.setHourCount(entry.getHourCount());
        entryResponse.setCreated(entry.getCreated());
        entryResponse.setUpdated(entry.getUpdated());
        return entryResponse;
    }

    @Override
    public Iterable<EntryResponse> getAll() {
        Iterable<Entry> entries = entryRepository.findAll();
        ArrayList<EntryResponse> entryResponses = new ArrayList<>();

        for (Entry entry : entries) {
            entryResponses.add(convertToResponse(entry));
        }

        return entryResponses;
    }

    @Override
    public EntryResponse get(Long id) {
        Entry e = entryRepository.findById(id).orElse(null);

        if (e == null)
            return null;

        return convertToResponse(e);
    }

    @Override
    public EntryResponse add(AddEntryBody entry) {
        if (!userRepository.existsById(entry.getUserId()))
            return null;

        if (!entryTypeRepository.existsById(entry.getTypeId()))
            return null;

        Entry e = new Entry();
        e.setUserId(entry.getUserId());
        e.setTypeId(entry.getTypeId());
        e.setDescription(entry.getDescription());
        e.setHourCount(entry.getHourCount());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        e.setCreated(now);
        e.setUpdated(now);
        return convertToResponse(entryRepository.save(e));
    }

    @Override
    public EntryResponse update(UpdateEntryBody entry) {
        if (!entryRepository.existsById(entry.getId()))
            return null;

        if (!entryTypeRepository.existsById(entry.getTypeId()))
            return null;

        Entry e = entryRepository.findById(entry.getId()).get();
        e.setTypeId(entry.getTypeId());
        e.setDescription(entry.getDescription());
        e.setHourCount(entry.getHourCount());
        e.setUpdated(new Timestamp(System.currentTimeMillis()));
        return convertToResponse(entryRepository.save(e));
    }

    @Override
    public Boolean delete(Long id) {
        if (!entryRepository.existsById(id))
            return false;

        Entry entry = entryRepository.findById(id).get();
        entryRepository.delete(entry);
        return true;
    }
}
