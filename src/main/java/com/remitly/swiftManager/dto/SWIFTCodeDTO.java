package com.remitly.swiftManager.dto;

import java.util.List;

public record SWIFTCodeDTO (String address, String bankName, String countryISO2Code, String countryName, boolean isHeadquarter, String swiftCode,List<BranchDTO> branches ) {


}
