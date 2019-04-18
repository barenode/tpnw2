package tpnw2.report;

import java.util.List;

public class TimeDataset<T extends TimeRecord> extends Dataset<T> {
	private static final long serialVersionUID = 1L;

	public TimeDataset(List<T> records, List<String> yKeys, List<String> labels) {
		super(records, "date", yKeys, labels);
	}
}
