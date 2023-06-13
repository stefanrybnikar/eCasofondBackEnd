package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.model.Company;
import com.pwc.ecasofond.request.body.add.AddCompanyBody;
import com.pwc.ecasofond.request.body.update.UpdateCompanyBody;
import com.pwc.ecasofond.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/company")
@Tag(name = "Company")
public class CompanyController implements com.pwc.ecasofond.controller.Controller<Company, AddCompanyBody, UpdateCompanyBody> {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    @GetMapping(path = "/all")
    @Operation(summary = "Gets all companies")
    public ResponseEntity<Iterable<Company>> getAll() {
        return ResponseEntity.ok(companyService.getAll());
    }

    @Override
    @GetMapping(path = "/{id}")
    @Operation(summary = "Gets a company")
    public ResponseEntity<Company> get(
            @PathVariable(name = "id")
            @Parameter(description = "Id of company")
            Long id
    ) {
        Company c = companyService.get(id);

        if (c == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @PostMapping(path = "/add")
    @Operation(summary = "Adds a company")
    public ResponseEntity<Company> add(@RequestBody AddCompanyBody requestBody) {
        Company c = companyService.add(requestBody);

        if (c == null)
            return ResponseEntity.internalServerError().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @PutMapping(path = "/update")
    @Operation(summary = "Updates a company")
    public ResponseEntity<Company> update(@RequestBody UpdateCompanyBody requestBody) {
        Company c = companyService.update(requestBody);

        if (c == null)
            return ResponseEntity.internalServerError().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    @Operation(summary = "Deletes a company")
    public ResponseEntity<Boolean> delete(
            @PathVariable(name = "id")
            @Parameter(description = "Id of company")
            Long id
    ) {
        Boolean result = companyService.delete(id);

        if (!result)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().build();
    }
}
