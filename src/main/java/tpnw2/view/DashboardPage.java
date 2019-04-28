package tpnw2.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import tpnw2.persistence.OrderDao;
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
	
	@SpringBean	private ReportDao reportDao;
	@SpringBean	private OrderDao orderDao;
	
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
	}
}
