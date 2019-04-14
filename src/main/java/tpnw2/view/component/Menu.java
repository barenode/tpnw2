package tpnw2.view.component;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import tpnw2.view.OrderPage;
import tpnw2.view.PageBase;

@SuppressWarnings("serial")
public class Menu extends Panel {

	public Menu(String id) {
		super(id);
		add(new PageLink<>("orders", OrderPage.class));
		add(new PageLink<>("employees", OrderPage.class));
		add(new PageLink<>("orders", OrderPage.class));
		add(new PageLink<>("orders", OrderPage.class));
		add(new PageLink<>("orders", OrderPage.class));
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
