package tpnw2.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import tpnw2.domain.Car;
import tpnw2.domain.CarCriteria;
import tpnw2.persistence.CarDao;
import tpnw2.view.component.MasterDetail;

@SuppressWarnings("serial")
public class CarPage extends PageBase {

	@SpringBean CarDao carDao;
	
	public CarPage(final PageParameters parameters) {
		super(parameters);		
		MasterDetail<Car, CarCriteria> main = new MasterDetail<Car, CarCriteria>("main", carDao) {
			@Override
			protected Car newItem() {
				return new Car();
			}

			@Override
			protected Panel itemPanel(String id, IModel<Car> model) {
				return new CarPanel(id, model);
			}

			@Override
			protected List<? extends IColumn<Car, String>> getColumns() {
				List<IColumn<Car, String>> columns = new ArrayList<>();		
				columns.add(new PropertyColumn<>(new ResourceModel("numberplate"), "numberplate", "numberplate"));
				columns.add(new PropertyColumn<>(new ResourceModel("owner"), "owner", "owner"));
				return columns;
			}

			@Override
			protected CarCriteria newCriteria() {
				return new CarCriteria();
			}

			@Override
			protected Panel criteriaPanel(String id, IModel<CarCriteria> model) {
				return new CarCriteriaPanel(id, model);
			}

			@Override
			protected String defaultSortProperty() {
				return "numberplate";
			}

			@Override
			protected boolean checkBeforePersist(Car item) {
				//error("Duplicate city");
				return true;
			}			
		}; 
		add(main);		
	}
}
