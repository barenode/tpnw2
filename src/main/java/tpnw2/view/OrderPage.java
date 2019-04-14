package tpnw2.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import tpnw2.domain.Individual;
import tpnw2.domain.Order;
import tpnw2.domain.OrderCriteria;
import tpnw2.domain.OrderStatus;
import tpnw2.persistence.OrderDao;
import tpnw2.view.component.MasterDetail;

@SuppressWarnings("serial")
public class OrderPage  extends PageBase {

	@SpringBean OrderDao orderDao;
	
	public OrderPage(final PageParameters parameters) {
		super(parameters);		
		MasterDetail<Order, OrderCriteria> main = new MasterDetail<Order, OrderCriteria>("main", orderDao) {
			@Override
			protected Order newItem() {
				Order order = new Order();
				order.setStatus(OrderStatus.Pending);
				order.setDepartureDate(new Date());
				order.setClient(new Individual());
				return order;
			}

			@Override
			protected Panel itemPanel(String id, IModel<Order> model) {
				return new OrderPanel(id, model);
			}

			@Override
			protected List<? extends IColumn<Order, String>> getColumns() {
				List<IColumn<Order, String>> columns = new ArrayList<>();		
				columns.add(new PropertyColumn<>(new ResourceModel("id"), "id", "id"));
				columns.add(new PropertyColumn<>(new ResourceModel("status"), "status", "status"));
				return columns;
			}

			@Override
			protected OrderCriteria newCriteria() {
				return new OrderCriteria();
			}

			@Override
			protected Panel criteriaPanel(String id, IModel<OrderCriteria> model) {
				return new OrderCriteriaPanel(id, model);
			}

			@Override
			protected String defaultSortProperty() {
				return "id";
			}

			@Override
			protected boolean checkBeforePersist(Order item) {
				//error("Duplicate city");
				return true;
			}			
		}; 
		add(main);		
	}
}
