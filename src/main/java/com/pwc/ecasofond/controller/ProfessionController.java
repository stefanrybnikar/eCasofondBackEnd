package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.model.Profession;
import com.pwc.ecasofond.request.body.add.AddProfessionBody;
import com.pwc.ecasofond.request.body.update.UpdateProfessionBody;
import com.pwc.ecasofond.service.ProfessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/profession")
@Tag(name = "Profession")
public class ProfessionController implements com.pwc.ecasofond.controller.Controller<Profession, AddProfessionBody, UpdateProfessionBody> {
    private final ProfessionService professionService;

    public ProfessionController(ProfessionService professionService) {
        this.professionService = professionService;
    }

    @Override
    @GetMapping(path = "/all")
    @Operation(summary = "Gets all professions")
    public ResponseEntity<Iterable<Profession>> getAll() {
        return ResponseEntity.ok(professionService.getAll());
    }

    @Override
    @GetMapping(path = "/{id}")
    @Operation(summary = "Gets a profession")
    public ResponseEntity<Profession> get(
            @PathVariable(name = "id")
            @Parameter(description = "Id of profession")
            Long id
    ) {
        Profession c = professionService.get(id);

        if (c == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @PostMapping(path = "/add")
    @Operation(summary = "Adds a profession")
    public ResponseEntity<Profession> add(@RequestBody AddProfessionBody requestBody) {
        Profession c = professionService.add(requestBody);

        if (c == null)
            return ResponseEntity.internalServerError().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @PutMapping(path = "/update")
    @Operation(summary = "Updates a profession")
    public ResponseEntity<Profession> update(@RequestBody UpdateProfessionBody requestBody) {
        Profession c = professionService.update(requestBody);

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
        Boolean result = professionService.delete(id);

        if (!result)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().build();
    }
}
