package com.safecare.abdm.transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentDetailsRepo extends JpaRepository<ConsentDetails, Integer> {

	ConsentDetails findByConsentId(String id);

	List<ConsentDetails> findAllByStatus(int i);

}
