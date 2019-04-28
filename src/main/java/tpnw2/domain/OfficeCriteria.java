package tpnw2.domain;

import java.io.Serializable;

public class OfficeCriteria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String city;
	private Integer managerId;
	
	public OfficeCriteria() {
		super();
	}
	
	public OfficeCriteria(String city) {
		super();
		this.city = city;
	}
	
	public OfficeCriteria(Integer managerId) {
		super();
		this.managerId = managerId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}	

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	@Override
	public String toString() {
		return "OfficeCriteria [city=" + city + ", managerId=" + managerId + "]";
	}
}
