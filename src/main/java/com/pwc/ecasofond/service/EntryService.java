package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.Entry;
import com.pwc.ecasofond.repository.EntryRepository;
import com.pwc.ecasofond.repository.EntryTypeRepository;
import com.pwc.ecasofond.repository.UserRepository;
import com.pwc.ecasofond.request.body.add.AddEntryBody;
import com.pwc.ecasofond.request.body.update.UpdateEntryBody;

import java.sql.Timestamp;

@org.springframework.stereotype.Service
public class EntryService implements Service<Entry, AddEntryBody, UpdateEntryBody> {
    private final EntryRepository entryRepository;
    private final UserRepository userRepository;
    private final EntryTypeRepository entryTypeRepository;

    public EntryService(EntryRepository entryRepository, UserRepository userRepository, EntryTypeRepository entryTypeRepository) {
        this.entryRepository = entryRepository;
        this.userRepository = userRepository;
        this.entryTypeRepository = entryTypeRepository;
    }

    @Override
    public Iterable<Entry> getAll() {
        return entryRepository.findAll();
    }

    @Override
    public Entry get(Long id) {
        return entryRepository.findById(id).orElse(null);
    }

    @Override
    public Entry add(AddEntryBody entry) {
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
        return entryRepository.save(e);
    }

    @Override
    public Entry update(UpdateEntryBody entry) {
        if (!entryRepository.existsById(entry.getId()))
            return null;

        if (!entryTypeRepository.existsById(entry.getTypeId()))
            return null;

        Entry e = entryRepository.findById(entry.getId()).get();
        e.setTypeId(entry.getTypeId());
        e.setDescription(entry.getDescription());
        e.setHourCount(entry.getHourCount());
        e.setUpdated(new Timestamp(System.currentTimeMillis()));
        return entryRepository.save(e);
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
