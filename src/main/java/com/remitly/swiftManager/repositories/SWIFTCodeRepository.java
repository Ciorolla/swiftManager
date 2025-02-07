package com.remitly.swiftManager.repositories;

import com.remitly.swiftManager.dto.BranchDTO;
import com.remitly.swiftManager.dto.SWIFTCodeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.remitly.swiftManager.model.SWIFTCode;

import java.util.List;
import java.util.Optional;

@Repository
public interface SWIFTCodeRepository extends JpaRepository<SWIFTCode,Integer>{

    Optional<SWIFTCode> findBySWIFTCode(String swiftCode);
    Optional<List<SWIFTCode>> findByHeadquarterSWIFT(String headquarterSWIFT);

}
