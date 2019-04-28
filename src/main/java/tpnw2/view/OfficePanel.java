package tpnw2.view;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.StringValidator;

import tpnw2.domain.Office;

@SuppressWarnings("serial")
public class OfficePanel extends Panel {

	public OfficePanel(String id, IModel<Office> model) {
		super(id);
		add(new TextField<String>("city", new PropertyModel<>(model, "city")).setRequired(true)
			.add(StringValidator.maximumLength(200)));
		add(new EmployeeChoice("manager", new PropertyModel<>(model, "manager")));
	}	
}
