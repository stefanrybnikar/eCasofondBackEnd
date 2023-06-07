package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.model.Entry;
import com.pwc.ecasofond.request.body.add.AddEntryBody;
import com.pwc.ecasofond.request.body.update.UpdateEntryBody;
import com.pwc.ecasofond.service.EntryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/entry")
public class EntryController implements com.pwc.ecasofond.controller.Controller<Entry, AddEntryBody, UpdateEntryBody> {
    @Autowired
    private EntryService entryService;

    @Override
    @GetMapping(path = "/all")
    @Operation(summary = "Gets all entries")
    public ResponseEntity<Iterable<Entry>> getAll()
    {
        return ResponseEntity.ok(entryService.getAll());
    }

    @Override
    @GetMapping(path = "/{id}")
    @Operation(summary = "Gets and entry")
    public ResponseEntity<Entry> get(
            @PathVariable(name = "id")
            @Parameter(description = "Id of entry")
            Long id
    ) {
        Entry e = entryService.get(id);

        if (e == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(e);
    }

    @Override
    @PostMapping(path = "/add")
    @Operation(summary = "Adds an entry")
    public ResponseEntity<Entry> add(@RequestBody AddEntryBody requestBody) {
        Entry e = entryService.add(requestBody);

        if (e == null)
            return ResponseEntity.internalServerError().build();
        else
            return ResponseEntity.ok(e);
    }

    @Override
    @PostMapping(path = "/update")
    @Operation(summary = "Updates an entry")
    public ResponseEntity<Entry> update(@RequestBody UpdateEntryBody requestBody) {
        Entry e = entryService.update(requestBody);

        if (e == null)
            return ResponseEntity.internalServerError().build();
        else
            return ResponseEntity.ok(e);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    @Operation(summary = "Deletes an entry")
    public ResponseEntity<Boolean> delete(
            @PathVariable(name = "id")
            @Parameter(description = "Id of entry")
            Long id
    ) {
        Boolean result = entryService.delete(id);

        if (!result)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().build();
    }
}
