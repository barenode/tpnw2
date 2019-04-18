package tpnw2.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import tpnw2.report.Dataset;
import tpnw2.report.MorrisChart;
import tpnw2.report.Record;
import tpnw2.report.persistence.ReportDao;

@AuthorizeInstantiation("SIGNEDIN")
@SuppressWarnings("serial")
public class DashboardPage extends PageBase {
	DateFormat df = new SimpleDateFormat("dd.MM.yyyy");	
	DateFormat dformat = new SimpleDateFormat("dd");	
	//DateFormat df = new SimpleDateFormat("dd");
	
	@SpringBean
	private ReportDao reportDao;
	
	public DashboardPage(PageParameters parameters) {
		super(parameters);
		add(new Dashboard("main"));
		
	}
	
	private final class Dashboard extends Panel {

		public Dashboard(String id) {
			super(id);
			add(new MorrisChart<>("test-chart", "Bar", ds()));
		}		
	}

	private Dataset<Record> ds() {
		return reportDao.dataset();
//		try {
//			List<TestRecord> records = Arrays.asList(
//				new TestRecord(df.parse("01.04.2019"), 10.0),
//				new TestRecord(df.parse("02.04.2019"), 1.0),
//				new TestRecord(df.parse("03.04.2019"), 7.0),
//				new TestRecord(df.parse("04.04.2019"), 18.0),
//				new TestRecord(df.parse("05.04.2019"), 3.0),
//				new TestRecord(df.parse("06.04.2019"), 22.0),
//				new TestRecord(df.parse("07.04.2019"), 11.0),
//				new TestRecord(df.parse("08.04.2019"), 9.0),
//				new TestRecord(df.parse("09.04.2019"), 16.0),
//				new TestRecord(df.parse("10.04.2019"), 2.0)
//			);
//			return new Dataset<TestRecord>(records, "date", Arrays.asList("value"), Arrays.asList("value"));
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
	}
	
//	private static class TestRecord implements Record {
//		
//		private final Date date; 
//		private final Double value; 
//		
//		public TestRecord(Date date, Double value) {
//			super();
//			this.date = date;
//			this.value = value;
//		}
//
//		public Date getDate() {
//			return date;
//		}
//
//		public Double getValue() {
//			return value;
//		}
//
//		@Override
//		public Map<String, String> map() {
//			Map<String, String> result = new HashMap<>();
//			result.put("date", format(date));
//			result.put("value", format(value));
//			return result;
//		}		
//	}
}
