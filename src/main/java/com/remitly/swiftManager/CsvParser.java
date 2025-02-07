package com.remitly.swiftManager;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.remitly.swiftManager.model.Bank;
import com.remitly.swiftManager.model.BankAddress;
import com.remitly.swiftManager.model.Country;
import com.remitly.swiftManager.model.SWIFTCode;
import com.remitly.swiftManager.repositories.BankAddressRepository;
import com.remitly.swiftManager.repositories.BankRepository;
import com.remitly.swiftManager.repositories.CountryRepository;
import com.remitly.swiftManager.repositories.SWIFTCodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CsvParser {
    private static final Logger logger = LoggerFactory.getLogger(CsvParser.class);

    private final BankAddressRepository bankAddressRepository;
    private final CountryRepository countryRepository;
    private final BankRepository bankRepository;
    private final SWIFTCodeRepository codeRepository;

    public CsvParser(BankAddressRepository bankAddressRepository, CountryRepository countryRepository, BankRepository bankRepository, SWIFTCodeRepository codeRepository) {
        this.bankAddressRepository = bankAddressRepository;
        this.countryRepository = countryRepository;
        this.bankRepository = bankRepository;
        this.codeRepository = codeRepository;
    }

    public List<SWIFTCode> parseCSV(String filePath) {
        List<SWIFTCode> swiftCodes = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // Skip header

            while ((line = reader.readNext()) != null) {
                Country country = new Country();
                country.setCountryISO2Code(line[0].trim().toUpperCase());
                country.setCountryName(line[6].trim().toUpperCase());
                countryRepository.save(country);


                String[] finalLine = line;
                Bank bank1 = (bankRepository.findByNameAndCountry(line[3].trim(), country)
                        .orElseGet(() -> {
                            Bank bank = new Bank();
                            bank.setName(finalLine[3].trim());
                            bank.setcountryISOCode(country);
                            return bankRepository.save(bank);
                        }));

                SWIFTCode swiftCode = new SWIFTCode();
                swiftCode.setSWIFTCode(line[1].trim());
                swiftCode.setCodeType(line[2].trim());
                swiftCode.setHeadquarter(line[1].endsWith("XXX"));
                System.out.println(line[1].substring(0, 8) + "XXX");
                swiftCode.setHeadquarterSWIFT(swiftCode.isHeadquarter() ? null : line[1].substring(0, 8) + "XXX");
                swiftCode.setBank(bank1);
                codeRepository.save(swiftCode);

                BankAddress address = new BankAddress();
                address.setAddress(line[4].trim());
                address.setTownName(line[5].trim());
                address.setTimeZone(line[7].trim());
                address.setSwiftCode(swiftCode);
                bankAddressRepository.save(address);

                swiftCodes.add(swiftCode);
            }
        } catch (IOException | CsvValidationException e) {
            logger.error("Error reading CSV file", e);
        }
        return swiftCodes;
    }
}