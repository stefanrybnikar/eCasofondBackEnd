package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.model.EntryType;
import com.pwc.ecasofond.request.body.add.AddEntryTypeBody;
import com.pwc.ecasofond.request.body.update.UpdateEntryTypeBody;
import com.pwc.ecasofond.request.response.EntryTypeResponse;
import com.pwc.ecasofond.service.EntryTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/entrytype")
@Tag(name = "Entry Type")
public class EntryTypeController implements com.pwc.ecasofond.controller.Controller<EntryTypeResponse, AddEntryTypeBody, UpdateEntryTypeBody> {
    private final EntryTypeService entryTypeService;

    public EntryTypeController(EntryTypeService entryTypeService) {
        this.entryTypeService = entryTypeService;
    }


    @Override
    @GetMapping(path = "/all")
    @Operation(summary = "Gets all entry types")
    public ResponseEntity<Iterable<EntryTypeResponse>> getAll() {
        return ResponseEntity.ok(entryTypeService.getAll());
    }

    @Override
    @GetMapping(path = "/{id}")
    @Operation(summary = "Gets an entry type")
    public ResponseEntity<EntryTypeResponse> get(
            @PathVariable(name = "id")
            @Parameter(description = "Id of entry type")
            Long id
    ) {
        EntryTypeResponse c = entryTypeService.get(id);

        if (c == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @PostMapping(path = "/add")
    @Operation(summary = "Adds an entry type")
    public ResponseEntity<EntryTypeResponse> add(@RequestBody AddEntryTypeBody requestBody) {
        EntryTypeResponse c = entryTypeService.add(requestBody);

        if (c == null)
            return ResponseEntity.internalServerError().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @PutMapping(path = "/update")
    @Operation(summary = "Updates an entry type")
    public ResponseEntity<EntryTypeResponse> update(@RequestBody UpdateEntryTypeBody requestBody) {
        EntryTypeResponse c = entryTypeService.update(requestBody);

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
