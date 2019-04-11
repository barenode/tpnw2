package tpnw2.report;

import java.io.Serializable;
import java.util.List;

public class Dataset<T extends Record> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final List<T> records;
	private final String xKey;
	private final List<String> yKeys;
	private final List<String> labels;
	
	public Dataset(List<T> records) {
		this(records, null, null, null);
	}
	
	public Dataset(List<T> records, String xKey, List<String> yKeys, List<String> labels) {
		super();
		this.records = records;
		this.xKey = xKey;
		this.yKeys = yKeys;
		this.labels = labels;
	}

	public List<T> getRecords() {
		return records;
	}

	public String getXKey() {
		return xKey;
	}

	public List<String> getYKeys() {
		return yKeys;
	}	
	
	public List<String> getLabels() {
		return labels;
	}		
}
