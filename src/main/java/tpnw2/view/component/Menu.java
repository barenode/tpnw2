package tpnw2.view.component;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import tpnw2.Auth;
import tpnw2.view.CarPage;
import tpnw2.view.CompanyPage;
import tpnw2.view.DashboardPage;
import tpnw2.view.EmployeePage;
import tpnw2.view.OfficePage;
import tpnw2.view.OrderPage;
import tpnw2.view.PageBase;

@SuppressWarnings("serial")
public class Menu extends Panel {

	public Menu(String id) {
		super(id);
		add(new PageLink<>("dashboard", DashboardPage.class));
		add(new PageLink<>("orders", OrderPage.class));
		add(Auth.render(new PageLink<>("employees", EmployeePage.class), 	Auth.ADMINISTRATOR));
		add(Auth.render(new PageLink<>("offices", OfficePage.class), 		Auth.ADMINISTRATOR));
		add(Auth.render(new PageLink<>("cars", CarPage.class), 				Auth.ADMINISTRATOR));
		add(Auth.render(new PageLink<>("clients", CompanyPage.class),		Auth.ADMINISTRATOR));
	}
	
	private static final class PageLink<T extends PageBase> extends WebMarkupContainer {		
		private final Class<T> clazz;
		
		public PageLink(String id, Class<T> clazz) {
			super(id);
			this.clazz = clazz;
		}
		
		@Override
		protected void onInitialize() {
			super.onInitialize();
			 add(new Link<T>("link"){
				 @Override
					public void onClick() {
						setResponsePage(clazz);			
					}
			 });
		}	
	}
}
