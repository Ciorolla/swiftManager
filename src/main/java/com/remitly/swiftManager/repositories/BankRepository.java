package com.remitly.swiftManager.repositories;

import com.remitly.swiftManager.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remitly.swiftManager.model.Bank;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {
    Optional<Bank> findByNameAndCountry(String bankCode, Country country);
}
