package com.remitly.swiftManager.controler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.remitly.swiftManager.CsvParser;

@Controller
@RequestMapping("/v1")
@Tag(name = "CSV Import", description = "Endpoints for importing CSV files")
public class CsvImportController {
    private final CsvParser csvParser;

    public CsvImportController(CsvParser csvParser) {
        this.csvParser = csvParser;
    }

    @GetMapping("/import")
    @Operation(summary = "Import CSV", description = "Imports a CSV file into the database.")
    public String importCsv() {
        try {
            csvParser.parseCSV("src/main/resources/swift.csv");
            return "CSV file imported successfully!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
