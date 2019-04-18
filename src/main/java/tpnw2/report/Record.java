package tpnw2.report;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

public interface Record extends Serializable {
	
	DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Serializes record to JSON.
	 */
	Map<String, String> map();
	
	
	default String format(Object o) {
		if (o==null) {
			return "''";
		} else if (o instanceof String) {
			return "'" + o + "'";
		} else if (o instanceof Number) {
			return String.valueOf(o);
		} else if (o instanceof Date) {
			return "'" + format((Date)o) + "'";
		} else if (o instanceof LocalDate) {
			return "'" + ((LocalDate)o).format(DateTimeFormatter.ISO_DATE) + "'";
		} else {
			return "'" + o.toString() + "'";
		}
	}
	
	default String format(Date o) {
		try {
			return FORMAT.format(o);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
