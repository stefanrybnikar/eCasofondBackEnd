package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.request.body.add.AddCompanyBody;
import com.pwc.ecasofond.request.body.update.UpdateCompanyBody;
import com.pwc.ecasofond.request.response.ApiResponse;
import com.pwc.ecasofond.request.response.CompanyResponse;
import com.pwc.ecasofond.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/v1/company")
@Tag(name = "Company")
public class CompanyController implements com.pwc.ecasofond.controller.Controller<CompanyResponse, AddCompanyBody, UpdateCompanyBody> {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    @GetMapping(path = "/all")
    @Operation(summary = "Gets all companies")
    public ResponseEntity<ApiResponse<Iterable<CompanyResponse>>> getAll() {
        ApiResponse<Iterable<CompanyResponse>> result = companyService.getAll();

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @Override
    @GetMapping(path = "/{id}")
    @Operation(summary = "Gets a company")
    public ResponseEntity<ApiResponse<CompanyResponse>> get(
            @PathVariable(name = "id")
            @Parameter(description = "Id of company")
            Long id
    ) {
        ApiResponse<CompanyResponse> result = companyService.get(id);

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @Override
    @PostMapping(path = "/add")
    @Operation(summary = "Adds a company")
    public ResponseEntity<ApiResponse<CompanyResponse>> add(@RequestBody AddCompanyBody requestBody) {
        ApiResponse<CompanyResponse> result = companyService.add(requestBody);

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @Override
    @PutMapping(path = "/update")
    @Operation(summary = "Updates a company")
    public ResponseEntity<ApiResponse<CompanyResponse>> update(@RequestBody UpdateCompanyBody requestBody) {
        ApiResponse<CompanyResponse> result = companyService.update(requestBody);

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    @Operation(summary = "Deletes a company")
    public ResponseEntity<ApiResponse<Boolean>> delete(
            @PathVariable(name = "id")
            @Parameter(description = "Id of company")
            Long id
    ) {
        ApiResponse<Boolean> result = companyService.delete(id);

        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
