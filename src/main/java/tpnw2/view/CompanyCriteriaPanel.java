package tpnw2.view;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import tpnw2.domain.CompanyCriteria;

@SuppressWarnings("serial")
public class CompanyCriteriaPanel extends Panel {

	public CompanyCriteriaPanel(String id, IModel<CompanyCriteria> model) {
		super(id);
		add(new TextField<String>("name", new PropertyModel<>(model, "name")));
	}
}
