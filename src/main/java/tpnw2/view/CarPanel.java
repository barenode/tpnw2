package tpnw2.view;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import tpnw2.domain.Car;

@SuppressWarnings("serial")
public class CarPanel extends Panel {

	public CarPanel(String id, IModel<Car> model) {
		super(id);
		add(new TextField<String>("numberplate", new PropertyModel<>(model, "numberplate")).setRequired(true));
		add(new EmployeeChoice("owner", new PropertyModel<>(model, "owner")).setRequired(true));
	}
}
