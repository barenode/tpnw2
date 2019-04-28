package tpnw2.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import tpnw2.Auth;
import tpnw2.domain.Company;
import tpnw2.domain.Individual;
import tpnw2.domain.Order;
import tpnw2.domain.OrderCriteria;
import tpnw2.domain.OrderStatus;
import tpnw2.persistence.OrderDao;
import tpnw2.view.component.EnumModel;
import tpnw2.view.component.MasterDetail;

@AuthorizeInstantiation(Auth.SIGNEDIN)
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
				order.setAdministrator(Auth.logged());
				order.setDepartureDate(new Date());
				order.setClient(new Individual());
				order.setOffice(Auth.office());
				return order;
			}			

			@Override
			protected boolean newItemEnabled() {
				return Auth.isAdministrator() || Auth.isManager();
			}

			@Override
			protected Panel itemPanel(String id, IModel<Order> model) {
				return new OrderPanel(id, model);
			}			
			
			@Override
			protected boolean removeEnabled(IModel<Order> model) {
				Order order = model.getObject();
				return order.getStatus()==OrderStatus.Assigned || order.getStatus()==OrderStatus.Pending;
			}

			private void fillOrder(IModel<Order> model) {
				replace(new OrderFillPanel("content", model) {
					@Override
					protected void onSubmit(IModel<Order> model) {
						Order order = model.getObject();
						order.setStatus(OrderStatus.Filled);
						if (order.getClient() instanceof Company) {
							Company company = (Company)order.getClient();
							order.setPrice(
								company.getContract().getPricePerKm().multiply(new BigDecimal(order.getDistance())));
						}
						orderDao.save(order);
						master();				
					}

					@Override
					protected void onCancel() {
						master();						
					}					
				});
			}

			@Override
			@SuppressWarnings("unchecked")
			protected List<? extends IColumn<Order, String>> getColumns() {
				List<IColumn<Order, String>> columns = new ArrayList<>();		
				columns.add(new PropertyColumn<>(new ResourceModel("id"), "id", "id"));
				columns.add(new PropertyColumn<>(new ResourceModel("departureDate"), "departureDate", "departureDate"));
				columns.add(new PropertyColumn<Order, String>(new ResourceModel("status"), "status", "status") {
					@Override
					public IModel<?> getDataModel(IModel<Order> rowModel) {
						IModel<OrderStatus> statusModel = (IModel<OrderStatus>)super.getDataModel(rowModel);
						return new EnumModel<>(statusModel);
					}					
				});
				columns.add(new PropertyColumn<>(new ResourceModel("administrator"), "administrator", "administrator"));
				columns.add(new PropertyColumn<>(new ResourceModel("driver"), "driver", "driver"));
				columns.add(new LinkColumn<Order>(new ResourceModel("button.fill")){			
					protected void onClick(IModel<Order> model) {
						fillOrder(model);
					}

					@Override
					protected boolean isEnabled(IModel<Order> model) {
						Order order = model.getObject();
						return order.getStatus()==OrderStatus.Assigned;
					}					
				});
				columns.add(new LinkColumn<Order>(new ResourceModel("button.cancel")){			
					protected void onClick(IModel<Order> model) {
						Order order = model.getObject();
						order.setStatus(OrderStatus.Cancelled);
						orderDao.save(order);
					}

					@Override
					protected boolean isEnabled(IModel<Order> model) {
						Order order = model.getObject();
						return order.getStatus()==OrderStatus.Assigned || order.getStatus()==OrderStatus.Pending;
					}					
				});
				return columns;
			}

			@Override
			protected OrderCriteria newCriteria() {
				OrderCriteria criteria = new OrderCriteria();
				if (!Auth.isAdministrator()) {
					criteria.setEmployee(Auth.logged());
				}
				return criteria;
			}

			@Override
			protected Panel criteriaPanel(String id, IModel<OrderCriteria> model) {
				return new OrderCriteriaPanel(id, model);
			}

			@Override
			protected String defaultSortProperty() {
				return "departureDate";
			}						

			@Override
			protected SortOrder defaultSortOrder() {
				return SortOrder.DESCENDING;
			}

			@Override
			protected boolean checkBeforePersist(Order order) {
				if (order.getDriver().getCars()==null || order.getDriver().getCars().isEmpty()) {
					error(getString("error.order.driver.car"));
					return false;
				}
				//assign
				if (order.getStatus()==OrderStatus.Pending && order.getDriver()!=null) {
					order.setStatus(OrderStatus.Assigned);
				}
				return true;
			}			
		}; 
		add(main);		
	}
}
