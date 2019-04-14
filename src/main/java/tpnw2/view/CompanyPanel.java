package tpnw2.view;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import tpnw2.domain.Company;

@SuppressWarnings("serial")
public class CompanyPanel extends Panel {

	public CompanyPanel(String id, IModel<Company> model) {
		super(id);
		add(new TextField<String>("name", new PropertyModel<>(model, "name")).setRequired(true));
		add(new TextField<String>("phone", new PropertyModel<>(model, "phone")).setRequired(true));		
	}
}
