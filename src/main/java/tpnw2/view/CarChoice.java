package tpnw2.view;

import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;

import tpnw2.domain.Car;

@SuppressWarnings("serial")
public class CarChoice extends DropDownChoice<Car> {

	public CarChoice(String id, IModel<Car> model, List<Car> choices) {
		super(id, model, choices);
		setChoiceRenderer(new CarChoiceRenderer());
	}	
	 
	private static final class CarChoiceRenderer extends ChoiceRenderer<Car> {

		@Override
		public String getIdValue(Car object, int index) {
			return String.valueOf(object.getId());
		}
	}
}
