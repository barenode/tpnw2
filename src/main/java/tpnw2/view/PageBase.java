package tpnw2.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import tpnw2.view.component.Header;
import tpnw2.view.component.Menu;

@SuppressWarnings("serial")
public class PageBase extends WebPage {

	public PageBase(final PageParameters parameters) {
		super(parameters);
		add(new Label("title", new ResourceModel("title")));
		add(new Header("header"));
		add(new Menu("menu"));
	}
}
