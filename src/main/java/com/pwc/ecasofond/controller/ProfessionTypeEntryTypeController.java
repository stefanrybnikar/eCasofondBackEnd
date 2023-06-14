package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.model.ProfessionTypeEntryType;
import com.pwc.ecasofond.request.body.add.AddProfessionTypeEntryTypeBody;
import com.pwc.ecasofond.request.body.update.UpdateProfessionTypeEntryTypeBody;
import com.pwc.ecasofond.service.ProfessionTypeEntryTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/professiontypeentrytype")
@Tag(name = "Profession Type Entry Type")
public class ProfessionTypeEntryTypeController implements Controller<ProfessionTypeEntryType, AddProfessionTypeEntryTypeBody, UpdateProfessionTypeEntryTypeBody> {
    private final ProfessionTypeEntryTypeService professionTypeEntryTypeService;

    public ProfessionTypeEntryTypeController(ProfessionTypeEntryTypeService professionTypeEntryTypeService) {
        this.professionTypeEntryTypeService = professionTypeEntryTypeService;
    }

    @Override
    @GetMapping(path = "/all")
    @Operation(summary = "Gets all profession type X entry type relations")
    public ResponseEntity<Iterable<ProfessionTypeEntryType>> getAll() {
        return ResponseEntity.ok(professionTypeEntryTypeService.getAll());
    }

    @Override
    @GetMapping(path = "/{id}")
    @Operation(summary = "Gets a profession type X entry type relation")
    public ResponseEntity<ProfessionTypeEntryType> get(
            @PathVariable(name = "id")
            @Parameter(description = "Id of profession type X entry type relation")
            Long id
    ) {
        ProfessionTypeEntryType c = professionTypeEntryTypeService.get(id);
        if (c == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @PostMapping(path = "/add")
    @Operation(summary = "Adds a profession type X entry type relation")
    public ResponseEntity<ProfessionTypeEntryType> add(AddProfessionTypeEntryTypeBody requestBody) {
        ProfessionTypeEntryType c = professionTypeEntryTypeService.add(requestBody);

        if (c == null)
            return ResponseEntity.internalServerError().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @PutMapping(path = "/update")
    @Operation(summary = "Updates a profession type X entry type relation")
    public ResponseEntity<ProfessionTypeEntryType> update(UpdateProfessionTypeEntryTypeBody requestBody) {
        ProfessionTypeEntryType c = professionTypeEntryTypeService.update(requestBody);

        if (c == null)
            return ResponseEntity.internalServerError().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    @Operation(summary = "Deletes a profession type X entry type relation")
    public ResponseEntity<Boolean> delete(
            @PathVariable(name = "id")
            @Parameter(description = "Id of profession type X entry type relation")
            Long id
    ) {
        Boolean result = professionTypeEntryTypeService.delete(id);

        if (!result)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().build();
    }
}
