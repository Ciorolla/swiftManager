package com.remitly.swiftManager.service;

import com.remitly.swiftManager.dto.AddSwiftDTO;
import com.remitly.swiftManager.dto.BranchDTO;
import com.remitly.swiftManager.dto.ListByCountryDTO;
import com.remitly.swiftManager.dto.SWIFTCodeDTO;
import com.remitly.swiftManager.model.Bank;
import com.remitly.swiftManager.model.BankAddress;
import com.remitly.swiftManager.model.Country;
import com.remitly.swiftManager.model.SWIFTCode;
import com.remitly.swiftManager.repositories.BankAddressRepository;
import com.remitly.swiftManager.repositories.BankRepository;
import com.remitly.swiftManager.repositories.CountryRepository;
import com.remitly.swiftManager.repositories.SWIFTCodeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SWIFTCodeService {
    private final BankAddressRepository bankAddressRepository;
    private final BankRepository bankRepository;
    private final CountryRepository countryRepository;
    private final SWIFTCodeRepository codeRepository;

    @Autowired
    public SWIFTCodeService(BankAddressRepository bankAddressRepository, BankRepository bankRepository, CountryRepository countryRepository, SWIFTCodeRepository codeRepository) {
        this.bankAddressRepository = bankAddressRepository;
        this.bankRepository = bankRepository;
        this.countryRepository = countryRepository;
        this.codeRepository = codeRepository;
    }

    public SWIFTCodeDTO findSWIFTCodeDetails(String swiftCode) {
        System.out.println("Fetching data for SWIFT code: " + swiftCode);
        SWIFTCode headquarter = codeRepository.findBySWIFTCode(swiftCode)
                .orElse(null);

        if (headquarter == null) {
            return null;
        }

        Bank bank = headquarter.getBank();
        Country country = bank.getCountry();
        BankAddress bankAddress = headquarter.getAddresses().stream().findFirst().orElse(null);



        List<BranchDTO> branchEntities2 = codeRepository.findByHeadquarterSWIFT(swiftCode).orElse(Collections.emptyList())
                .stream()
                .map(branch -> {
                    Bank bankBranch = branch.getBank();
                    Country countryBranch = bankBranch.getCountry();
                    BankAddress bankAddressBranch = branch.getAddresses().stream().findFirst().orElse(null);
                            assert bankAddressBranch != null;
                            return new BranchDTO(
                             bankAddressBranch.getAddress(),
                             bankBranch.getName(),
                             countryBranch.getCountryISO2Code(),
                             branch.isHeadquarter(),
                             branch.getSWIFTCode()
                    );
                }
                )
                .toList();

        return new SWIFTCodeDTO(
                bankAddress.getAddress(),
                bank.getName(),
                country.getCountryISO2Code(),
                country.getCountryName(),
                headquarter.isHeadquarter(),
                headquarter.getSWIFTCode(),
                branchEntities2
        );


    }

    @Transactional
    public String addSwiftCodeService(AddSwiftDTO AddSwiftDTO) {
        Country country = countryRepository.findByCountryISO2Code(AddSwiftDTO.countryISO2())
                .orElseGet( ()->{
                    Country newCountry = new Country();
                    newCountry.setCountryISO2Code(AddSwiftDTO.countryISO2());
                    newCountry.setCountryName(AddSwiftDTO.countryName());
                    return countryRepository.save(newCountry);
                });
        Bank bank = bankRepository.findByNameAndCountry(AddSwiftDTO.bankName(), country)
                .orElseGet(()->{
                    Bank newBank = new Bank();
                    newBank.setName(AddSwiftDTO.bankName());
                    newBank.setCountry(country);
                    return bankRepository.save(newBank);
                });

        SWIFTCode swiftCode = new SWIFTCode();
        swiftCode.setBank(bank);
        swiftCode.setSWIFTCode(AddSwiftDTO.swiftCode());
        swiftCode.setHeadquarter(AddSwiftDTO.isHeadquarter());
        swiftCode.setCodeType("BIC11");
        swiftCode.setHeadquarterSWIFT(swiftCode.isHeadquarter() ? null : AddSwiftDTO.swiftCode().substring(0, 8) + "XXX");
        codeRepository.save(swiftCode);

        BankAddress bankAddress = new BankAddress();
        bankAddress.setAddress(AddSwiftDTO.address());
        bankAddress.setSwiftCode(swiftCode);
        bankAddressRepository.save(bankAddress);


        return "success";
    }

    public ListByCountryDTO getSwiftCodesByCountry(String countryISO2) {
        System.out.println("Fetching data for countryISO2: " + countryISO2);
        Country country = countryRepository.findByCountryISO2Code(countryISO2)
                .orElse(null);

        if (country == null) {
            return null;
        }


        List<BranchDTO> branchDTOs = country.getBanks().stream()
                .flatMap(bank -> bank.getSwiftCodes().stream()
                        .flatMap(swiftCode -> swiftCode.getAddresses().stream()
                                .map(address -> new BranchDTO(
                                        address.getAddress(),
                                        bank.getName(),
                                        country.getCountryISO2Code(),
                                        swiftCode.isHeadquarter(),
                                        swiftCode.getSWIFTCode()
                                ))
                        )
                )
                .collect(Collectors.toList());

        return new ListByCountryDTO(
                country.getCountryISO2Code(),
                country.getCountryName(),
                branchDTOs
        );
    }

    @Transactional
    public String deleteSwiftCode(String swiftCode) {
        SWIFTCode swiftCode1 = codeRepository.findBySWIFTCode(swiftCode)
                .orElse(null);

        if (swiftCode1 == null) {
            return null;
        }

        for (BankAddress address : swiftCode1.getAddresses()) {
            Bank bank = swiftCode1.getBank();
            Country country = bank.getCountry();
            codeRepository.delete(swiftCode1);
            bankRepository.delete(bank);
            bankAddressRepository.delete(address);
            countryRepository.delete(country);
        }

        return "success";
    }

}
