package com.remitly.swiftManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remitly.swiftManager.model.BankAddress;

@Repository
public interface BankAddressRepository extends JpaRepository<BankAddress, Integer> {
}
