package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.request.body.add.AddProfessionTypeEntryTypeBody;
import com.pwc.ecasofond.request.body.update.UpdateProfessionTypeEntryTypeBody;
import com.pwc.ecasofond.request.response.ApiResponse;
import com.pwc.ecasofond.request.response.ProfessionTypeEntryTypeResponse;
import com.pwc.ecasofond.service.ProfessionTypeEntryTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/v1/professiontypeentrytype")
@Tag(name = "Profession Type Entry Type")
public class ProfessionTypeEntryTypeController implements Controller<ProfessionTypeEntryTypeResponse, AddProfessionTypeEntryTypeBody, UpdateProfessionTypeEntryTypeBody> {
    private final ProfessionTypeEntryTypeService professionTypeEntryTypeService;

    public ProfessionTypeEntryTypeController(ProfessionTypeEntryTypeService professionTypeEntryTypeService) {
        this.professionTypeEntryTypeService = professionTypeEntryTypeService;
    }

    @Override
    @GetMapping(path = "/all")
    @Operation(summary = "Gets all profession type X entry type relations")
    @PreAuthorize("hasAnyAuthority('ADVISOR', 'ADMIN', 'USER')")
    public ResponseEntity<ApiResponse<Iterable<ProfessionTypeEntryTypeResponse>>> getAll() {
        ApiResponse<Iterable<ProfessionTypeEntryTypeResponse>> result = professionTypeEntryTypeService.getAll();

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @Override
    @GetMapping(path = "/{id}")
    @Operation(summary = "Gets a profession type X entry type relation")
    @PreAuthorize("hasAnyAuthority('ADVISOR', 'ADMIN', 'USER')")
    public ResponseEntity<ApiResponse<ProfessionTypeEntryTypeResponse>> get(
            @PathVariable(name = "id")
            @Parameter(description = "Id of profession type X entry type relation")
            Long id
    ) {
        ApiResponse<ProfessionTypeEntryTypeResponse> c = professionTypeEntryTypeService.get(id);

        return ResponseEntity.status(c.getStatus()).body(c);
    }

    @Override
    @PostMapping(path = "/add")
    @Operation(summary = "Adds a profession type X entry type relation")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<ProfessionTypeEntryTypeResponse>> add(@RequestBody AddProfessionTypeEntryTypeBody requestBody) {
        ApiResponse<ProfessionTypeEntryTypeResponse> c = professionTypeEntryTypeService.add(requestBody);

        return ResponseEntity.status(c.getStatus()).body(c);
    }

    @Override
    @PutMapping(path = "/update")
    @Operation(summary = "Updates a profession type X entry type relation")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<ProfessionTypeEntryTypeResponse>> update(@RequestBody UpdateProfessionTypeEntryTypeBody requestBody) {
        ApiResponse<ProfessionTypeEntryTypeResponse> c = professionTypeEntryTypeService.update(requestBody);

        return ResponseEntity.status(c.getStatus()).body(c);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    @Operation(summary = "Deletes a profession type X entry type relation")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<Boolean>> delete(
            @PathVariable(name = "id")
            @Parameter(description = "Id of profession type X entry type relation")
            Long id
    ) {
        ApiResponse<Boolean> result = professionTypeEntryTypeService.delete(id);
        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
