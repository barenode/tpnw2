package tpnw2.report;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

public class SVTimeRecord<T extends Serializable> extends TimeRecord {
	private static final long serialVersionUID = 1L;

	private T value;
	
	public SVTimeRecord(LocalDate date, T value) {
		super(date);
		this.value = value;
	}

	@Override
	public Map<String, String> map() {
		Map<String, String> map = super.map();
		map.put("value", format(value));
		return map;
	}
}
