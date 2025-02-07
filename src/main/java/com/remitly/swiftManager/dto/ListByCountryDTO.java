package com.remitly.swiftManager.dto;

import java.util.List;

public record ListByCountryDTO(String countryISO2,String countryName,List<BranchDTO> BranchDTOList) {

}
