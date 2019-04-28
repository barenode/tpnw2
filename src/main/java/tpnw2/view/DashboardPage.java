package tpnw2.view;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import tpnw2.Auth;
import tpnw2.domain.Employee;
import tpnw2.domain.Office;
import tpnw2.persistence.OrderDao;
import tpnw2.report.Dataset;
import tpnw2.report.MorrisChart;
import tpnw2.report.Record;
import tpnw2.report.persistence.ReportDao;

@AuthorizeInstantiation(Auth.SIGNEDIN)
@SuppressWarnings("serial")
public class DashboardPage extends PageBase {
	
	@SpringBean	private ReportDao reportDao;
	@SpringBean	private OrderDao orderDao;
	
	public DashboardPage(PageParameters parameters) {
		super(parameters);
		add(new Dashboard("main"));		
	}
	
	private final class Dashboard extends Panel {

		public Dashboard(String id) {
			super(id);
			//counts
			WebMarkupContainer ordercountChart = new WebMarkupContainer("ordercountChart") {
				@Override
				public boolean isVisible() {
					return Auth.isAdministrator() || Auth.isManager();
				}				
			};
			add(ordercountChart);
			ordercountChart.add(new MorrisChart<>("chart", "Bar", ordercount()));		
			ordercountChart.add(new Label("chartLabel", chartLabel()));			
			//ratios
			WebMarkupContainer ratioChart = new WebMarkupContainer("ratioChart");
			add(ratioChart);
			ratioChart.add(new MorrisChart<>("chart", "Donut", ratio()));
			ratioChart.add(new Label("chartLabel", chartLabel()));			
		}		
	}		
	
	private String chartLabel() {
		if (Auth.isAdministrator()) {
			return "";
		} else if (Auth.isManager()) {
			return " - " + Auth.office();
		} else {
			return " - " + Auth.logged();
		}
	}

	private Dataset<Record> ordercount() {
		Office office = null;
		if (!Auth.isAdministrator()) {
			office = Auth.office();
		}
		return reportDao.orderCount(office);
	}
	
	private Dataset<Record> ratio() {
		Office office = null;
		Employee driver = null;
		if (!Auth.isAdministrator()) {
			office = Auth.office();
			if (!Auth.isManager()) {
				driver = Auth.logged();
			}
		}				
		return reportDao.orderRatio(office, driver);
	}
}
