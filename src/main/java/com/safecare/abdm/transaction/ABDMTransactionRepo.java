package com.safecare.abdm.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ABDMTransactionRepo extends JpaRepository<ABDMTransaction, Integer> {

	ABDMTransaction findByRequestId(String requestId);

}
