package com.pwc.ecasofond;

import com.pwc.ecasofond.model.Company;
import com.pwc.ecasofond.model.Employee;
import com.pwc.ecasofond.model.Entry;
import com.pwc.ecasofond.repository.CompanyRepository;
import com.pwc.ecasofond.repository.EmployeeRepository;
import com.pwc.ecasofond.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
@RequestMapping(path = "/api")
public class DatabaseController {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EntryRepository entryRepository;

    // ==================== Company ====================
    @GetMapping(path = "/company/all")
    public @ResponseBody Iterable<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @GetMapping(path = "/company")
    public @ResponseBody Company getCompany(@RequestParam Integer id) {
        return companyRepository.findById(id).orElse(null);
    }

    @PostMapping(path = "/company/add")
    public @ResponseBody String addCompany(@RequestParam String name) {
        Company company = new Company();
        company.setName(name);
        companyRepository.save(company);
        return "Saved";
    }

    @PostMapping(path = "/company/update")
    public @ResponseBody String updateCompany(@RequestParam Integer id, @RequestParam String name) {
        if (companyRepository.findById(id).isPresent()) {
            Company company = companyRepository.findById(id).get();
            company.setName(name);
            companyRepository.save(company);
            return "Updated";
        } else {
            return "Company not found";
        }
    }

    @DeleteMapping(path = "/company/delete")
    public @ResponseBody String deleteCompany(@RequestParam Integer id) {
        if (companyRepository.findById(id).isPresent()) {
            companyRepository.deleteById(id);
            return "Deleted";
        } else {
            return "Company not found";
        }
    }

    // ==================== Employee ====================

    @GetMapping(path = "/employee/all")
    public @ResponseBody Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping(path = "/employee")
    public @ResponseBody Employee getEmployee(@RequestParam Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @PostMapping(path = "/employee/add")
    public @ResponseBody String addEmployee(@RequestParam Integer companyId, @RequestParam String fullName, @RequestParam String email) {
        Employee employee = new Employee();
        if (companyRepository.findById(companyId).isPresent()) {
            employee.setCompany(companyRepository.findById(companyId).get());
            employee.setFullName(fullName);
            employee.setEmail(email);
            employeeRepository.save(employee);
            return "Saved";
        } else {
            return "Company not found";
        }
    }

    @PostMapping(path = "/employee/update")
    public @ResponseBody String updateEmployee(@RequestParam Integer id, @RequestParam String fullName, @RequestParam String email) {
        if (employeeRepository.findById(id).isPresent()) {
            Employee employee = employeeRepository.findById(id).get();
            employee.setFullName(fullName);
            employee.setEmail(email);
            employeeRepository.save(employee);
            return "Updated";
        } else {
            return "Employee not found";
        }
    }

    @DeleteMapping(path = "/employee/delete")
    public @ResponseBody String deleteEmployee(@RequestParam Integer id) {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
            return "Deleted";
        } else {
            return "Employee not found";
        }
    }

    // ==================== Entry ====================

    @GetMapping(path = "/entry/all")
    public @ResponseBody Iterable<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

    @GetMapping(path = "/entry")
    public @ResponseBody Entry getEntry(@RequestParam Integer id) {
        return entryRepository.findById(id).orElse(null);
    }

    @PostMapping(path = "/entry/add")
    public @ResponseBody String addEntry(@RequestParam Integer employeeId, @RequestParam Integer type, @RequestParam String description, @RequestParam Integer hourCount) {
        Entry entry = new Entry();
        if (employeeRepository.findById(employeeId).isPresent()) {
            entry.setEmployeeId(employeeRepository.findById(employeeId).get());
            entry.setTimestamp(new Timestamp(System.currentTimeMillis()));
            entry.setType(type);
            entry.setDescription(description);
            entry.setHourCount(hourCount);
            entryRepository.save(entry);
            return "Saved";
        } else {
            return "Employee not found";
        }
    }

    @PostMapping(path = "/entry/update")
    public @ResponseBody String updateEntry(@RequestParam Integer id, @RequestParam Integer type, @RequestParam String description, @RequestParam Integer hourCount) {
        if (entryRepository.findById(id).isPresent()) {
            Entry entry = entryRepository.findById(id).get();
            entry.setTimestamp(entry.getTimestamp());
            entry.setType(type);
            entry.setDescription(description);
            entry.setHourCount(hourCount);
            entryRepository.save(entry);
            return "Updated";
        } else {
            return "Entry not found";
        }
    }

    @DeleteMapping(path = "/entry/delete")
    public @ResponseBody String deleteEntry(@RequestParam Integer id) {
        if (entryRepository.findById(id).isPresent()) {
            entryRepository.deleteById(id);
            return "Deleted";
        } else {
            return "Entry not found";
        }
    }
}
