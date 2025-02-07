package com.remitly.swiftManager.controler;

import com.remitly.swiftManager.dto.AddSwiftDTO;
import com.remitly.swiftManager.dto.ListByCountryDTO;
import com.remitly.swiftManager.dto.SWIFTCodeDTO;
import com.remitly.swiftManager.service.SWIFTCodeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/swift-codes")
@Tag(name = "SWIFT Codes", description = "Endpoints for managing SWIFT codes")
public class SWIFTCodesController {
    final private SWIFTCodeService swiftCodeService;

    public SWIFTCodesController(SWIFTCodeService swiftCodeService) {
        this.swiftCodeService = swiftCodeService;
    }

    @GetMapping("/{swiftcode}")
    @Operation(
            summary = "test1",
            description = "test1"
    )
    public ResponseEntity<SWIFTCodeDTO> getSwiftCode(@PathVariable String swiftcode) {
        SWIFTCodeDTO response = swiftCodeService.findSWIFTCodeDetails(swiftcode);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/country/{countryISO2code}")
    @Operation(
            summary = "Get SWIFT Code by countryISO2code",
            description = "Return all SWIFT codes with details for a specific country (both headquarters and branches)."
    )
    public ResponseEntity<ListByCountryDTO> getSwiftCodesByCountry(@PathVariable String countryISO2code) {
        ListByCountryDTO response  = swiftCodeService.getSwiftCodesByCountry(countryISO2code);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    @Operation(
            summary = "add Data",
            description = "test2"
    )
    public ResponseEntity<String> addSwiftCode(@RequestBody AddSwiftDTO AddSwiftDTO) {
        String response  = swiftCodeService.addSwiftCodeService(AddSwiftDTO);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{swift-code}")
    @Operation(
            summary = "Delete swift-code",
            description = "Deletes swift-code data if swiftCode matches the one in the database."
    )
    public ResponseEntity<String> deleteSwiftCode(@PathVariable String swiftCode) {
        String message = swiftCodeService.deleteSwiftCode(swiftCode);
        return ResponseEntity.ok().body(message);
    }
    
    
    
    
}

