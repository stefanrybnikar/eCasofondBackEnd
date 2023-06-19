package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.Entry;
import com.pwc.ecasofond.repository.EntryRepository;
import com.pwc.ecasofond.repository.EntryTypeRepository;
import com.pwc.ecasofond.repository.UserRepository;
import com.pwc.ecasofond.request.body.add.AddEntryBody;
import com.pwc.ecasofond.request.body.update.UpdateEntryBody;
import com.pwc.ecasofond.request.response.ApiResponse;
import com.pwc.ecasofond.request.response.EntryResponse;
import org.springframework.http.HttpStatus;

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
        entryResponse.setDay(entry.getDay());
        return entryResponse;
    }

    @Override
    public ApiResponse<Iterable<EntryResponse>> getAll() {
        ApiResponse<Iterable<EntryResponse>> response = new ApiResponse<>();
        Iterable<Entry> entries = entryRepository.findAll();
        ArrayList<EntryResponse> entryResponses = new ArrayList<>();

        for (Entry entry : entries) {
            entryResponses.add(convertToResponse(entry));
        }

        response.setData(entryResponses);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Found " + entryResponses.size() + " entries");

        return response;
    }

    @Override
    public ApiResponse<EntryResponse> get(Long id) {
        ApiResponse<EntryResponse> response = new ApiResponse<>();
        Entry e = entryRepository.findById(id).orElse(null);

        if (e == null) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Entry not found");
            return response;
        }

        response.setData(convertToResponse(e));
        response.setStatus(HttpStatus.OK);
        response.setMessage("Found entry");

        return response;
    }

    public ApiResponse<Iterable<EntryResponse>> getAllByUserId(Long id) {
        ApiResponse<Iterable<EntryResponse>> response = new ApiResponse<>();
        Iterable<Entry> entries = entryRepository.findAll();

        ArrayList<EntryResponse> entryResponses = new ArrayList<>();

        for (Entry entry : entries) {
            if (id.equals(entry.getUserId())) {
                entryResponses.add(convertToResponse(entry));
            }
        }

        response.setData(entryResponses);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Found " + entryResponses.size() + " entries");

        return response;
    }

    @Override
    public ApiResponse<EntryResponse> add(AddEntryBody entry) {
        ApiResponse<EntryResponse> response = new ApiResponse<>();
        if (!userRepository.existsById(entry.getUserId())) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("User not found");
            return response;
        }

        if (!entryTypeRepository.existsById(entry.getTypeId())) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Entry type not found");
            return response;
        }

        Entry e = new Entry();
        e.setUserId(entry.getUserId());
        e.setTypeId(entry.getTypeId());
        e.setDescription(entry.getDescription());
        e.setHourCount(entry.getHourCount());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        e.setCreated(now);
        e.setUpdated(now);
        e.setDay(entry.getDay());

        response.setData(convertToResponse(entryRepository.save(e)));
        response.setStatus(HttpStatus.OK);
        response.setMessage("Entry created");

        return response;
    }

    @Override
    public ApiResponse<EntryResponse> update(UpdateEntryBody entry) {
        ApiResponse<EntryResponse> response = new ApiResponse<>();

        if (!entryRepository.existsById(entry.getId())) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Entry not found");
            return response;
        }

        if (!entryTypeRepository.existsById(entry.getTypeId())) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Entry type not found");
            return response;
        }

        Entry e = entryRepository.findById(entry.getId()).get();
        e.setTypeId(entry.getTypeId());
        e.setDescription(entry.getDescription());
        e.setHourCount(entry.getHourCount());
        e.setUpdated(new Timestamp(System.currentTimeMillis()));
        e.setDay(entry.getDay());

        response.setData(convertToResponse(entryRepository.save(e)));
        response.setStatus(HttpStatus.OK);
        response.setMessage("Entry updated");

        return response;
    }

    @Override
    public ApiResponse<Boolean> delete(Long id) {
        ApiResponse<Boolean> response = new ApiResponse<>();

        if (!entryRepository.existsById(id)) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Entry not found");
            return response;
        }

        Entry entry = entryRepository.findById(id).get();
        entryRepository.delete(entry);

        response.setData(true);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Entry deleted");

        return response;
    }
}
