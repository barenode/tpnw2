package tpnw2.view;

import java.util.Collection;

import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import tpnw2.domain.Car;
import tpnw2.domain.CarCriteria;
import tpnw2.persistence.CarDao;

@SuppressWarnings("serial")
public class CarMultipleChoice extends ListMultipleChoice<Car> {

	@SpringBean CarDao carDao;
	
	@SuppressWarnings("all")
	public CarMultipleChoice(String id, IModel<? extends Collection<Car>> model) {
		super(id);
		IModel wrp = model;
		setModel(wrp);
		setChoices(carDao.findAll(new CarCriteria()));
	}
}
