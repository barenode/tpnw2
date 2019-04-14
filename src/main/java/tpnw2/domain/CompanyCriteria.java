package tpnw2.domain;

import java.io.Serializable;

public class CompanyCriteria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public CompanyCriteria() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
