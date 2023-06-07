package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.model.EntryType;
import com.pwc.ecasofond.request.body.add.AddEntryTypeBody;
import com.pwc.ecasofond.request.body.update.UpdateEntryTypeBody;
import com.pwc.ecasofond.service.EntryTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/entry-type")
public class EntryTypeController implements com.pwc.ecasofond.controller.Controller<EntryType, AddEntryTypeBody, UpdateEntryTypeBody>{
    @Autowired
    private EntryTypeService entryTypeService;


    @Override
    @GetMapping(path = "/all")
    @Operation(summary = "Gets all entry types")
    public ResponseEntity<Iterable<EntryType>> getAll() {
        return ResponseEntity.ok(entryTypeService.getAll());
    }

    @Override
    @GetMapping(path = "/{id}")
    @Operation(summary = "Gets an entry type")
    public ResponseEntity<EntryType> get(
            @PathVariable(name = "id")
            @Parameter(description = "Id of entry type")
            Long id
    ) {
        EntryType c = entryTypeService.get(id);

        if (c == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @PostMapping(path = "/add")
    @Operation(summary = "Adds an entry type")
    public ResponseEntity<EntryType> add(@RequestBody AddEntryTypeBody requestBody) {
        EntryType c = entryTypeService.add(requestBody);

        if (c == null)
            return ResponseEntity.internalServerError().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @PostMapping(path = "/update")
    @Operation(summary = "Updates an entry type")
    public ResponseEntity<EntryType> update(@RequestBody UpdateEntryTypeBody requestBody) {
        EntryType c = entryTypeService.update(requestBody);

        if (c == null)
            return ResponseEntity.internalServerError().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    @Operation(summary = "Deletes an entry type")
    public ResponseEntity<Boolean> delete(
            @PathVariable(name = "id")
            @Parameter(description = "Id of entry type")
            Long id
    ) {
        Boolean result = entryTypeService.delete(id);

        if (!result)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().build();
    }
}
