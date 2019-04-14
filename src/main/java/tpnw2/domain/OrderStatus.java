package tpnw2.domain;

public enum OrderStatus {

	Pending("P"),
	Assigned("A"),
	Cancelled("C"),
	Filled("F");
	
	private final String code;
	
	OrderStatus(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public static OrderStatus forCode(String code) {
		switch (code) {
			case "P" : return Pending;
			case "A" : return Assigned;
			case "C" : return Cancelled;
			case "F" : return Filled;
			default : return null;
		}
	}
}
