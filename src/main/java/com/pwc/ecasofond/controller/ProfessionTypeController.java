package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.model.ProfessionType;
import com.pwc.ecasofond.request.body.add.AddProfessionTypeBody;
import com.pwc.ecasofond.request.body.update.UpdateProfessionTypeBody;
import com.pwc.ecasofond.request.response.ProfessionTypeResponse;
import com.pwc.ecasofond.service.ProfessionTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/professiontype")
@Tag(name = "Profession")
public class ProfessionTypeController implements com.pwc.ecasofond.controller.Controller<ProfessionTypeResponse, AddProfessionTypeBody, UpdateProfessionTypeBody> {
    private final ProfessionTypeService professionTypeService;

    public ProfessionTypeController(ProfessionTypeService professionTypeService) {
        this.professionTypeService = professionTypeService;
    }

    @Override
    @GetMapping(path = "/all")
    @Operation(summary = "Gets all professions")
    public ResponseEntity<Iterable<ProfessionTypeResponse>> getAll() {
        return ResponseEntity.ok(professionTypeService.getAll());
    }

    @Override
    @GetMapping(path = "/{id}")
    @Operation(summary = "Gets a profession")
    public ResponseEntity<ProfessionTypeResponse> get(
            @PathVariable(name = "id")
            @Parameter(description = "Id of profession")
            Long id
    ) {
        ProfessionTypeResponse c = professionTypeService.get(id);

        if (c == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @PostMapping(path = "/add")
    @Operation(summary = "Adds a profession")
    public ResponseEntity<ProfessionTypeResponse> add(@RequestBody AddProfessionTypeBody requestBody) {
        ProfessionTypeResponse c = professionTypeService.add(requestBody);

        if (c == null)
            return ResponseEntity.internalServerError().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @PutMapping(path = "/update")
    @Operation(summary = "Updates a profession")
    public ResponseEntity<ProfessionTypeResponse> update(@RequestBody UpdateProfessionTypeBody requestBody) {
        ProfessionTypeResponse c = professionTypeService.update(requestBody);

        if (c == null)
            return ResponseEntity.internalServerError().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    @Operation(summary = "Deletes a profession")
    public ResponseEntity<Boolean> delete(
            @PathVariable(name = "id")
            @Parameter(description = "Id of profession")
            Long id
    ) {
        Boolean result = professionTypeService.delete(id);

        if (!result)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().build();
    }
}
