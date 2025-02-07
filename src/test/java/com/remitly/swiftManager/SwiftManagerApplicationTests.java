package com.remitly.swiftManager;

import com.remitly.swiftManager.model.Bank;
import com.remitly.swiftManager.model.Country;
import com.remitly.swiftManager.repositories.BankRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SwiftManagerApplicationTests {

	@Autowired
	private BankRepository bankRepository;

	@Test
	void testSaveAndFindBank() {
		Country country = new Country();
		country.setCountryISO2Code("PL");
		country.setCountryName("Poland");


		Bank bank = new Bank();
		bank.setName("Bank Polska");
		bank.setcountryISOCode(country);


		Bank savedBank = bankRepository.save(bank);


		assertThat(savedBank.getId()).isNotNull();
		assertThat(savedBank.getName()).isEqualTo("Bank Polska");
		assertThat(savedBank.getCountry().getCountryName()).isEqualTo("Poland");


		Optional<Bank> retrievedBank = bankRepository.findById(savedBank.getId());
		assertThat(retrievedBank).isPresent();
		assertThat(retrievedBank.get().getName()).isEqualTo("Bank Polska");
	}

}
