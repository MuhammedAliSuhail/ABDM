
package com.safecare.abdm.callback;

import java.util.List;

import lombok.Data;

@Data
public class Patient {

	public String id;
	public String name;
	public String gender;
	public Integer yearOfBirth;
	public Address address;
	public List<Identifier> identifiers = null;

}
