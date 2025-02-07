package com.remitly.swiftManager;

import com.remitly.swiftManager.model.Bank;
import com.remitly.swiftManager.model.BankAddress;
import com.remitly.swiftManager.model.Country;
import com.remitly.swiftManager.model.SWIFTCode;
import com.remitly.swiftManager.repositories.BankAddressRepository;
import com.remitly.swiftManager.repositories.BankRepository;
import com.remitly.swiftManager.repositories.CountryRepository;
import com.remitly.swiftManager.repositories.SWIFTCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwiftManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwiftManagerApplication.class, args);
//		var context = SpringApplication.run(SwiftManagerApplication.class, args);
//
//		// Wstrzyknięcie beanów do metody main
//		CountryRepository countryRepository = context.getBean(CountryRepository.class);
//		BankRepository bankRepository = context.getBean(BankRepository.class);
//		SWIFTCodeRepository swiftCodeRepository = context.getBean(SWIFTCodeRepository.class);
//		BankAddressRepository bankAddressRepository = context.getBean(BankAddressRepository.class);
//
//		// Dodanie przykładowych danych do bazy
//		Country country = new Country();
//		country.setCountryName("United States");  // Upewnij się, że ustawiasz nazwę kraju
//		country.setCountryISO2Code("US");         // Ustaw ISO2 kod
//		System.out.println("Country Name: " + country.getCountryName());  // Sprawdź, czy nazwa kraju jest ustawiona
//		countryRepository.save(country);          // Save the country first
//
//		Bank bank = new Bank();
//		bank.setName("Bank of America");
//		bank.setcountryISOCode(country);         // Now the country object has the correct country name.
//		bankRepository.save(bank);
//
//		SWIFTCode swiftCode = new SWIFTCode();
//		swiftCode.setBank(bank);
//		swiftCode.setSWIFTCode("BOFAUS3NXXX");
//		swiftCode.setCodeType("BIC");
//		swiftCode.setHeadquarter(true);
//		swiftCodeRepository.save(swiftCode);
//
//		BankAddress address = new BankAddress();
//		address.setSwiftCode(swiftCode);
//		address.setAddress("123 Main Street");
//		address.setTownName("New York");
//		address.setTimeZone("America/New_York");
//		bankAddressRepository.save(address);
//
//// Display the message in the log
//		System.out.println("Dane zostały dodane do bazy:");
//		System.out.println("Kraj: " + country.getCountryName());
//		System.out.println("Bank: " + bank.getName());
//		System.out.println("SWIFT: " + swiftCode.getSWIFTCode());
//		System.out.println("Adres: " + address.getAddress() + ", " + address.getTownName());
	}

}
