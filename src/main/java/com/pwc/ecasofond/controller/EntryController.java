package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.request.body.add.AddEntryBody;
import com.pwc.ecasofond.request.body.update.UpdateEntryBody;
import com.pwc.ecasofond.request.response.ApiResponse;
import com.pwc.ecasofond.request.response.EntryResponse;
import com.pwc.ecasofond.service.EntryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/v1/entry")
@Tag(name = "Entry")
public class EntryController implements com.pwc.ecasofond.controller.Controller<EntryResponse, AddEntryBody, UpdateEntryBody> {
    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @Override
    @GetMapping(path = "/all")
    @Operation(summary = "Gets all entries")
    @PreAuthorize("hasAnyAuthority('ADVISOR', 'ADMIN', 'USER')")
    public ResponseEntity<ApiResponse<Iterable<EntryResponse>>> getAll() {
        ApiResponse<Iterable<EntryResponse>> response = entryService.getAll();

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    @GetMapping(path = "/{id}")
    @Operation(summary = "Gets and entry")
    @PreAuthorize("hasAnyAuthority('ADVISOR', 'ADMIN', 'USER')")
    public ResponseEntity<ApiResponse<EntryResponse>> get(
            @PathVariable(name = "id")
            @Parameter(description = "Id of entry")
            Long id
    ) {
        ApiResponse<EntryResponse> result = entryService.get(id);

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping(path = "/user/{id}")
    @Operation(summary = "Gets all entries of a user")
    @PreAuthorize("hasAnyAuthority('ADVISOR', 'ADMIN', 'USER')")
    public ResponseEntity<ApiResponse<Iterable<EntryResponse>>> getAllByUserId(
            @PathVariable(name = "id")
            @Parameter(description = "Id of user")
            Long id
    ) {
        ApiResponse<Iterable<EntryResponse>> response = entryService.getAllByUserId(id);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    @PostMapping(path = "/add")
    @Operation(summary = "Adds an entry")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse<EntryResponse>> add(@RequestBody AddEntryBody requestBody) {
        ApiResponse<EntryResponse> result = entryService.add(requestBody);

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @Override
    @PutMapping(path = "/update")
    @Operation(summary = "Updates an entry")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse<EntryResponse>> update(@RequestBody UpdateEntryBody requestBody) {
        ApiResponse<EntryResponse> result = entryService.update(requestBody);

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    @Operation(summary = "Deletes an entry")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse<Boolean>> delete(
            @PathVariable(name = "id")
            @Parameter(description = "Id of entry")
            Long id
    ) {
        ApiResponse<Boolean> result = entryService.delete(id);

        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
