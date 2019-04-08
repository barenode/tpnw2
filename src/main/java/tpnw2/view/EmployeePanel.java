package tpnw2.view;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import tpnw2.domain.Employee;

@SuppressWarnings("serial")
public class EmployeePanel extends Panel {

	public EmployeePanel(String id, IModel<Employee> model) {
		super(id);
		add(new TextField<String>("firstname", new PropertyModel<>(model, "firstname")).setRequired(true));
		add(new TextField<String>("lastname", new PropertyModel<>(model, "lastname")).setRequired(true));
	}
}
