package com.safecare.abdm.transaction;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "consent_details")
public class ConsentDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String consentId;
	private String transactionId;
	private String patientId;
	private String careContextId;
	private String consentRequestId;
	private String transactionRequestId;
	private Integer status;
	private Date createdDate;

}
