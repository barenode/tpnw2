package tpnw2.view.component;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import tpnw2.report.ReportPage;
import tpnw2.view.CarPage;
import tpnw2.view.CompanyPage;
import tpnw2.view.EmployeePage;
import tpnw2.view.OfficePage;
import tpnw2.view.OrderPage;
import tpnw2.view.PageBase;

@SuppressWarnings("serial")
public class Menu extends Panel {

	public Menu(String id) {
		super(id);
		add(new PageLink<>("dashboard", ReportPage.class));
		add(new PageLink<>("orders", OrderPage.class));
		add(new PageLink<>("employees", EmployeePage.class));
		add(new PageLink<>("offices", OfficePage.class));
		add(new PageLink<>("cars", CarPage.class));
		add(new PageLink<>("clients", CompanyPage.class));
	}
	
	private static final class PageLink<T extends PageBase> extends Link<T> {		
		private final Class<T> clazz;
		
		public PageLink(String id, Class<T> clazz) {
			super(id);
			this.clazz = clazz;
		}

		@Override
		public void onClick() {
			setResponsePage(clazz);			
		}
	}
}
