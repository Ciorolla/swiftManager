package com.remitly.swiftManager.dto;

public record AddSwiftDTO (String address, String bankName, String countryISO2,String countryName, boolean isHeadquarter, String swiftCode) {
}
