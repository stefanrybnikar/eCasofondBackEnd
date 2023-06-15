package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.request.body.add.AddProfessionTypeBody;
import com.pwc.ecasofond.request.body.update.UpdateProfessionTypeBody;
import com.pwc.ecasofond.request.response.ApiResponse;
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
@RequestMapping(path = "/v1/professiontype")
@Tag(name = "Profession")
public class ProfessionTypeController implements com.pwc.ecasofond.controller.Controller<ProfessionTypeResponse, AddProfessionTypeBody, UpdateProfessionTypeBody> {
    private final ProfessionTypeService professionTypeService;

    public ProfessionTypeController(ProfessionTypeService professionTypeService) {
        this.professionTypeService = professionTypeService;
    }

    @Override
    @GetMapping(path = "/all")
    @Operation(summary = "Gets all professions")
    public ResponseEntity<ApiResponse<Iterable<ProfessionTypeResponse>>> getAll() {
        ApiResponse<Iterable<ProfessionTypeResponse>> result = professionTypeService.getAll();

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @Override
    @GetMapping(path = "/{id}")
    @Operation(summary = "Gets a profession")
    public ResponseEntity<ApiResponse<ProfessionTypeResponse>> get(
            @PathVariable(name = "id")
            @Parameter(description = "Id of profession")
            Long id
    ) {
        ApiResponse<ProfessionTypeResponse> c = professionTypeService.get(id);

        return ResponseEntity.status(c.getStatus()).body(c);
    }

    @Override
    @PostMapping(path = "/add")
    @Operation(summary = "Adds a profession")
    public ResponseEntity<ApiResponse<ProfessionTypeResponse>> add(@RequestBody AddProfessionTypeBody requestBody) {
        ApiResponse<ProfessionTypeResponse> c = professionTypeService.add(requestBody);

        return ResponseEntity.status(c.getStatus()).body(c);
    }

    @Override
    @PutMapping(path = "/update")
    @Operation(summary = "Updates a profession")
    public ResponseEntity<ApiResponse<ProfessionTypeResponse>> update(@RequestBody UpdateProfessionTypeBody requestBody) {
        ApiResponse<ProfessionTypeResponse> c = professionTypeService.update(requestBody);

        return ResponseEntity.status(c.getStatus()).body(c);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    @Operation(summary = "Deletes a profession")
    public ResponseEntity<ApiResponse<Boolean>> delete(
            @PathVariable(name = "id")
            @Parameter(description = "Id of profession")
            Long id
    ) {
        ApiResponse<Boolean> result = professionTypeService.delete(id);

        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
