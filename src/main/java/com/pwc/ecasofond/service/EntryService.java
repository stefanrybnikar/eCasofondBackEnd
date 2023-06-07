package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.Entry;
import com.pwc.ecasofond.repository.UserRepository;
import com.pwc.ecasofond.request.body.add.AddEntryBody;
import com.pwc.ecasofond.request.body.update.UpdateEntryBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.pwc.ecasofond.repository.EntryRepository;

import java.sql.Timestamp;

@Component
public class EntryService implements Service<Entry, AddEntryBody, UpdateEntryBody> {
    @Autowired
    private EntryRepository entryRepository;
    @Autowired
    private UserRepository userRepository;

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
        if (userRepository.findById(entry.getUserId()).isPresent()) {
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

        return null;
    }

    @Override
    public Entry update(UpdateEntryBody entry) {
        if (entryRepository.findById(entry.getId()).isPresent()) {
            Entry e = entryRepository.findById(entry.getId()).get();
            e.setTypeId(entry.getTypeId());
            e.setDescription(entry.getDescription());
            e.setHourCount(entry.getHourCount());
            e.setUpdated(new Timestamp(System.currentTimeMillis()));
            return entryRepository.save(e);
        }

        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if (entryRepository.findById(id).isPresent()) {
            Entry entry = entryRepository.findById(id).get();
            entryRepository.delete(entry);
            return true;
        }

        return false;
    }
}
