package tpnw2.report;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class TimeRecord implements Record {
	private static final long serialVersionUID = 1L;
	
	private final LocalDate date;
	
	public TimeRecord(LocalDate date) {
		super();
		this.date = date;
	}

	@Override
	public Map<String, String> map() {
		Map<String, String> result = new LinkedHashMap<>();
		result.put("date", format(date));
		return result;
	}	
}
