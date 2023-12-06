package com.safecare.abdm.transaction;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "abdm_transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ABDMTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String requestId;
	private String timeStamp;
	private String requestBody;
	private String responseBody;
	private Integer status;
	private String ip;
	private Date createdDate;
}
