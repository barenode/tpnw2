package tpnw2.domain;

import java.io.Serializable;

public class OfficeCriteria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String city;
	
	public OfficeCriteria() {
		super();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "OfficeCriteria [city=" + city + "]";
	}
}
