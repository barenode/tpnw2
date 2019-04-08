package tpnw2.view;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import tpnw2.domain.CarCriteria;

@SuppressWarnings("serial")
public class CarCriteriaPanel extends Panel {

	public CarCriteriaPanel(String id, IModel<CarCriteria> model) {
		super(id);
		add(new TextField<String>("numberplate", new PropertyModel<>(model, "numberplate")));
		add(new EmployeeChoice("owner", new PropertyModel<>(model, "owner")).setNullValid(true));
	}
}
