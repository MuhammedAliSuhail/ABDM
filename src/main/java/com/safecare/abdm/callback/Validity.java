
package com.safecare.abdm.callback;

import lombok.Data;

@Data
public class Validity {

	public String purpose;
	public Requester requester;
	public String expiry;
	public String limit;

}
