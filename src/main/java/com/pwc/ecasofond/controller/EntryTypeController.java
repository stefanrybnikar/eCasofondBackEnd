package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.request.body.add.AddEntryTypeBody;
import com.pwc.ecasofond.request.body.update.UpdateEntryTypeBody;
import com.pwc.ecasofond.request.response.ApiResponse;
import com.pwc.ecasofond.request.response.EntryTypeResponse;
import com.pwc.ecasofond.service.EntryTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/v1/entrytype")
@Tag(name = "Entry Type")
public class EntryTypeController implements com.pwc.ecasofond.controller.Controller<EntryTypeResponse, AddEntryTypeBody, UpdateEntryTypeBody> {
    private final EntryTypeService entryTypeService;

    public EntryTypeController(EntryTypeService entryTypeService) {
        this.entryTypeService = entryTypeService;
    }


    @Override
    @GetMapping(path = "/all")
    @Operation(summary = "Gets all entry types")
    @PreAuthorize("hasAnyAuthority('ADVISOR', 'ADMIN', 'USER')")
    public ResponseEntity<ApiResponse<Iterable<EntryTypeResponse>>> getAll() {
        ApiResponse<Iterable<EntryTypeResponse>> result = entryTypeService.getAll();

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @Override
    @GetMapping(path = "/{id}")
    @Operation(summary = "Gets an entry type")
    @PreAuthorize("hasAnyAuthority('ADVISOR', 'ADMIN', 'USER')")
    public ResponseEntity<ApiResponse<EntryTypeResponse>> get(
            @PathVariable(name = "id")
            @Parameter(description = "Id of entry type")
            Long id
    ) {
        ApiResponse<EntryTypeResponse> result = entryTypeService.get(id);

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @Override
    @PostMapping(path = "/add")
    @Operation(summary = "Adds an entry type")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<EntryTypeResponse>> add(@RequestBody AddEntryTypeBody requestBody) {
        ApiResponse<EntryTypeResponse> result = entryTypeService.add(requestBody);

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @Override
    @PutMapping(path = "/update")
    @Operation(summary = "Updates an entry type")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<EntryTypeResponse>> update(@RequestBody UpdateEntryTypeBody requestBody) {
        ApiResponse<EntryTypeResponse> result = entryTypeService.update(requestBody);

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    @Operation(summary = "Deletes an entry type")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<Boolean>> delete(
            @PathVariable(name = "id")
            @Parameter(description = "Id of entry type")
            Long id
    ) {
        ApiResponse<Boolean> result = entryTypeService.delete(id);

        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
