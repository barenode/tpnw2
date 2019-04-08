package tpnw2.view;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import tpnw2.domain.EmployeeCriteria;

@SuppressWarnings("serial")
public class EmployeeCriteriaPanel extends Panel {

	public EmployeeCriteriaPanel(String id, IModel<EmployeeCriteria> model) {
		super(id);
		add(new TextField<String>("lastname", new PropertyModel<>(model, "lastname")));
	}
}
